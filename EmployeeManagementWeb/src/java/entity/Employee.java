/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Employee {
    private int employeeId;
    private String employeeName;
    private Date employeeDOB;
    private boolean employeeGender;
    private String employeeAddress;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, Date employeeDOB, boolean employeeGender, String employeeAddress) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDOB = employeeDOB;
        this.employeeGender = employeeGender;
        this.employeeAddress = employeeAddress;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getEmployeeDOB() {
        return employeeDOB;
    }

    public void setEmployeeDOB(Date employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public boolean isEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(boolean employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }
    
    
    
}
