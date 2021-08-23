package com.company;

public class Main {

    public static void main(String[] args) {
        int resp = 0;
        do{
            Menu.exibeMenu();
            resp = Menu.LerReposta();

            switch (resp){
                case 1:
                    System.out.println("Teste 1");
                    break;
                case 2:
                    System.out.println("Teste 2");
                    break;
            }
        }while(resp != 2);
    }
}
