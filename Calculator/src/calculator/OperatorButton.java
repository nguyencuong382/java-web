/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author Admin
 */
public final class OperatorButton extends Button {

    private String cache, cache2;
    private Double lastSecond;
    boolean done = false;
    boolean isClickSpecial = false;

    public OperatorButton(Calculator mainCal) {
        super(mainCal);
        this.addListener(mainCal.getOperatorButtonsGroup());
    }

    public boolean checkSpecialButton(String btnName, boolean checkEqual) {
        if (checkEqual && btnName.equals("=")) {
            return true;
        }

        String[] specialBtns = {"1/x", "%", "sqrt"};

        for (String specialBtn : specialBtns) {
            if (btnName.equals(specialBtn)) {
                return true;
            }
        }

        return false;
    }

    public void calculate(String operator) throws Exception {
        double result;
        Double value;

        System.out.println(operator);

        if (checkSpecialButton(cache, false)) {
            value = this.mainCal.getCacheValue();
            if (value == null) {
                value = this.mainCal.getCurrentValue();
            } else {
                this.mainCal.setCurrentValue(value);
            }
        } else {
            value = this.mainCal.getCurrentValue();
        }

        switch (operator) {
            case "+":
                result = value + lastSecond;
                break;
            case "-":
                result = value - lastSecond;
                break;
            case "/": {
                if (lastSecond == 0) {
                    throw new Exception("Can't device by zeros");
                }
                result = value / lastSecond;
                break;
            }
            case "1/x": {

                if (value == 0) {
                    throw new Exception("Can't device by zeros 1/x");
                }

                result = 1 / value;
                break;
            }
            case "+/-": {
                value = this.mainCal.getCacheValue();
                if (value == null) {
                    value = this.mainCal.getCurrentValue();
                } else {
                    this.mainCal.setCurrentValue(value);
                }
                result = value;
                break;
            }
            case "sqrt":
                if (value < 0) {
                    throw new Exception("Can't sprt");
                }
                result = Math.sqrt(this.mainCal.getCurrentValue());
                break;
            default:
                return;
        }
        this.mainCal.setCurrentValue(result);
        this.mainCal.displayWithFormat(result);
    }

    public void operatorHanlder(String operator) throws Exception {
        if (this.mainCal.getCacheValue() == null) {
            if (operator.equals("1/x")) {
                throw new Exception("Can't device by zeros");
            }
            return;
        }
        lastSecond = this.mainCal.getCacheValue();
        calculate(operator);
        this.mainCal.getDigitalButton().reset();
        done = false;
    }

    public void continueEqua(String operator) throws Exception {
        // right after program start,
        // user may type operator then type mutiple '=' so cache of second is null
        // or user want to calculate a special operation
        // we still remember last operator and consider it as first calculating
        if (lastSecond == null) {
            this.operatorHanlder(operator);
            return;
        }
        calculate(operator);
        this.mainCal.getDigitalButton().reset();
    }

    public void processFirst(String nameButton) throws Exception {
        System.out.println("first");
        this.setStateOperator(nameButton);

        if (nameButton.equals("=")) {
            this.mainCal.displayWithFormat(this.mainCal.getCurrentValue());
            this.mainCal.getDigitalButton().reset();
            lastSecond = this.mainCal.getCurrentValue();
        } else if (nameButton.equals("+/-")) {
            operatorHanlder(this.cache);
            Double value = -this.mainCal.getCurrentValue();
            this.mainCal.setCurrentValue(value);
            this.mainCal.displayWithFormat(value);
        } else if (checkSpecialButton(cache, false)) {
            System.out.println("special first");
            operatorHanlder(this.cache);
            isClickSpecial = true;
        }

        this.mainCal.getDigitalButton().reset();
    }

    public void process(String nameButton) {
        try {
            if (cache == null) {
                processFirst(nameButton);
                return;
            }

            if (!nameButton.equals(this.cache) || this.mainCal.getCacheValue() != null) {
                isClickSpecial = false;
            }

            if (nameButton.equals("=")) {
                // user calculated an operator then type a number then '='
                // we don't calculate again last operator
                // just display this number user typed
                if (checkSpecialButton(cache2, true)) {
                    if (this.mainCal.getCacheValue() != null) {
                        Double value = this.mainCal.getCacheValue();
                        this.mainCal.displayWithFormat(value);
                        this.mainCal.reset(false);
                        this.setStateOperator("=");
                        this.mainCal.setCurrentValue(value);
                        return;
                    }
                } else {
                    this.operatorHanlder(this.cache);
                    done = true;
                }

            } else {

                if (checkSpecialButton(nameButton, false) && isClickSpecial == false) {
                    System.out.println("special 1");
                    if (!checkSpecialButton(cache, false)) {
                        this.operatorHanlder(this.cache);
                        this.cache = nameButton;
                    }
                    this.cache = nameButton;
                    this.continueEqua(this.cache);
                    isClickSpecial = true;
                } // previous button is sqrt, the cache will save sqrt first
                // so if user type digital button right after sqrt, the cache is not reset
                // and if user type multiple operator, the 'sqrt' operator will be caculated
                else if (checkSpecialButton(cache, false)) {
                    System.out.println("special 2");
                    if (nameButton.equals("+/-")) {
                        continueEqua(nameButton);
                        Double value = -this.mainCal.getCurrentValue();
                        this.mainCal.setCurrentValue(value);
                        this.mainCal.displayWithFormat(value);
                        this.cache = nameButton;
                    } else {
                        this.cache = nameButton;
                        // Get value need to save
                        // user finish an special operator
                        // user may want new operation or continue calculating with currentValue
                        // the value is not null mean user want to continue claculating
                        // we just save state that user press another operator
                        Double value = this.mainCal.getCacheValue();
                        if (value != null) {
                            this.setStateOperator(nameButton);
                        }
                        this.mainCal.getDigitalButton().reset();
                    }

                } // handle user want to new operation
                // ex 3 * 2 = 6 then 2 - 2 = 4
                else if (this.mainCal.getCacheValue() != null && done) {
                    System.out.println("special 3");
                    Double currentValue = this.mainCal.getCacheValue();
                    this.mainCal.reset(false);
                    this.setStateOperator(nameButton);
                    this.mainCal.setCurrentValue(currentValue);

                } // the last cache may '=' because user type '=' but there aren't any operation run before
                // but user want to swith to another operation
                // we set state like cache is null at start
                else if (this.cache.equals("=") && this.mainCal.getCacheValue() != null) {
                    System.out.println("special 4");
                    this.setStateOperator(nameButton);
                    this.mainCal.getDigitalButton().reset();
                } // handle first operation
                else {
                    System.out.println("special 5");
                    operatorHanlder(this.cache);
                    if (nameButton.equals("+/-")) {
                        Double value = -this.mainCal.getCurrentValue();
                        this.mainCal.setCurrentValue(value);
                        this.mainCal.displayWithFormat(value);
                    }
                    this.cache = nameButton;

                }

                done = false;
            }
            this.cache2 = nameButton;
        } catch (Exception e) {
            System.out.println("error");
            this.mainCal.reset(false);
            this.mainCal.setCacheValue(this.mainCal.getCurrentValue());
            this.mainCal.getOperatorButton().process("=");
            this.mainCal.display("Error");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e
    ) {
        String nameButton = ((JButton) e.getSource()).getText();
        this.process(nameButton);
    }

    /**
     * This is a special and important of some processing When result was
     * calculated, user my switch to another operation take another number from
     * memory, digital button ... to run new operation Like 1/x -> 3 * 2 Before
     * swith these case, we switch state of button to prepare next computing
     * Example: User take 34 value from memory, so it looks like user press 34
     * then '=' but in this case, user don't type any '='
     *
     * @param nameButton name of button operator
     */
    public void setStateOperator(String nameButton) {
        this.cache = nameButton;
        this.cache2 = nameButton;
        Double value = this.mainCal.getCacheValue();
        value = value == null ? 0 : value;
        this.mainCal.setCurrentValue(value);
    }

    @Override
    public void reset() {
        cache = null;
        cache2 = null;
        lastSecond = null;
        isClickSpecial = false;
    }

    public void setIsClickSpecial(boolean isClickSpecial) {
        this.isClickSpecial = isClickSpecial;
    }
}
