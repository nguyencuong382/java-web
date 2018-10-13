/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.JButton;

/**
 *
 * @author Admin
 */
public class DigitalButton extends Button {

    private boolean isClickDotButton = false;
    String cache = "+0";

    public DigitalButton(Calculator mainCal) {
        super(mainCal);
        this.addListener(mainCal.getDigitalButtonsGroup());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btnName = ((JButton) e.getSource()).getText();
        if(this.cache.length() == 11) return ;
//        this.mainCal.getOperatorButton().setDone(false);
        switch (btnName) {
            case ".":
                if (!this.cache.contains(".")) {
                    this.cache += btnName;
                }
                isClickDotButton = true;
                break;
//            case "+/-":
//                if (this.mainCal.getCacheValue()== null && this.mainCal.getCurrentValue() != 0) {
//                    double text = -this.mainCal.getCurrentValue();
//                    this.mainCal.setCurrentValue(text);
//                    this.mainCal.displayWithFormat(text);
//                    this.mainCal.getOperatorButton().setIsClickSpecial(false);
//                    return;
//                }
//                if (cache.charAt(0) == '+') {
//                    this.cache = cache.replace('+', '-');
//                } else {
//                    this.cache = cache.replace('-', '+');
//                }
//                break;
            default:
                this.cache += btnName;
                break;
        }
        DecimalFormat df = new DecimalFormat("#.#");
        df.setMaximumFractionDigits(9);
        
        String value = df.format(this.getUserInput());
        String text = value.replaceFirst("\\.0*$|(\\.\\d*?)0+$", "$1");

        if (isClickDotButton) {
            if (!text.contains(".")) {
                text += ".";
            }
            if(this.cache.charAt(this.cache.length() - 1) == '0'){
                text += "0";
            }
        }
        this.mainCal.display(text);
        this.mainCal.setCacheValue(this.getUserInput());
        
//        if(btnName.equals("+/-")){
//            this.mainCal.getOperatorButton().process("=");
//        }
    }
    
    
    public double getUserInput() {
        return Double.parseDouble(this.cache);
    }
    
    @Override
    public void reset() {
        this.cache = "+0";
        this.isClickDotButton = false;
        this.mainCal.setCacheValue(null);
    }

}
