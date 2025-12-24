package com.mycompany.movieticketbookingapplication.views.customerViews;

import com.mycompany.movieticketbookingapplication.controllers.interfaces.customerControllersInterfaces.IShowController;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;

public class ConsoleShowView {
    private final ConsoleInputUtil inputReader;
    private final IShowController showController;

    public ConsoleShowView(IShowController showController) {
        inputReader = new ConsoleInputUtil();
        this.showController = showController;
    }
    
    public void runShowView() {
        
    }
}