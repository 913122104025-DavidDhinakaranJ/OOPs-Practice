package com.mycompany.bankapplication3.views;
import com.mycompany.bankapplication3.enums.MainOption;

public interface IMainView extends IMessageView {
    MainOption getMainChoice();
}