import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.LinkedList;



public class Calculator implements ActionListener {
    JFrame frame;
    JPanel buttonPanel = new JPanel();
    JPanel finalPanel = new JPanel();
    JButton oneButton,twoButton,threeButton,fourButton,fiveButton,sixButton,sevenButton,eightButton,nineButton,zeroButton, plusButton, minusButton, equalButton, multButton, divButton, pointButton, clearButton, erraseButton;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[6];
    JTextField textField = new JTextField();
    public static Deque<String> numberDeque = new LinkedList<>();
    Deque<String> copyForErase = new LinkedList<>();
    public static Boolean isFirstCalculation = true;
    Boolean firstIsEmpty = true;
    Boolean secondIsEmpty = true;
    Boolean hasBeenPressed = false;

    Font myFont = new Font("Comic Sans",Font.BOLD,30);

    public static double num1=0, num2=0, result=0;
    char symbol;

    public static double stringToDouble(Deque<String> numberDeque){
        int x = numberDeque.size();
        String theNumber = "";
        double numberDouble=num1;

        if(!numberDeque.isEmpty()){
            for(int i = 0; i<x;i++){
                theNumber = theNumber+numberDeque.pop();}

            numberDouble = Double.parseDouble(theNumber);
        }
        return numberDouble;
    }

/*    public static double eraseNumber(){
        double resultAfterErase = 0;

        if(isFirstCalculation && !numberDeque.isEmpty()){
            numberDeque.poll();
        }
        return resultAfterErase;
    }*/


    Calculator(){
        ImageIcon icon = new ImageIcon("img/icon.png");

        // textField
        textField.setBounds(2,10,335,75);
        textField.setFont(myFont);
        textField.setFocusable(false);

        //buttonPanel
        buttonPanel.setBounds(10,110,320,310);
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setLayout(new GridLayout(4,4,10,10));

        finalPanel.setBounds(10,450,320,50);
        //finalPanel.add(buttonPanel,BorderLayout.SOUTH);

        finalPanel.setBackground(Color.DARK_GRAY);
        finalPanel.setLayout(new GridLayout(1,2,10,10));

        //Declare Buttons
        oneButton = new JButton();
        twoButton = new JButton();
        threeButton = new JButton();
        fourButton = new JButton();
        fiveButton = new JButton();
        sixButton = new JButton();
        sevenButton = new JButton();
        eightButton = new JButton();
        nineButton = new JButton();
        zeroButton = new JButton();

        plusButton = new JButton("+");
        minusButton = new JButton("-");
        multButton = new JButton("*");
        divButton = new JButton("/");
        pointButton = new JButton(".");
        equalButton = new JButton("=");

        erraseButton = new JButton("Erase");
        clearButton = new JButton("Clear");


        numberButtons = new JButton[]{zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton};
        functionButtons = new JButton[]{pointButton, plusButton, minusButton, divButton, multButton, equalButton,erraseButton,clearButton};

        //Properties to Buttons
        for (int i=0;i<numberButtons.length;i++){
            numberButtons[i].setText(String.valueOf(i));
            numberButtons[i].setFocusable(false);
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
        }

        for (int i=0;i<functionButtons.length;i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setFont(myFont);
        }

        //add buttons ⌫
        buttonPanel.add(sevenButton);
        buttonPanel.add(eightButton);
        buttonPanel.add(nineButton);

        buttonPanel.add(minusButton);

        buttonPanel.add(fourButton);
        buttonPanel.add(fiveButton);
        buttonPanel.add(sixButton);

        buttonPanel.add(plusButton);

        buttonPanel.add(oneButton);
        buttonPanel.add(twoButton);
        buttonPanel.add(threeButton);

        buttonPanel.add(multButton);

        buttonPanel.add(zeroButton);
        buttonPanel.add(pointButton);
        buttonPanel.add(equalButton);

        buttonPanel.add(divButton);

        finalPanel.add(clearButton);
        finalPanel.add(erraseButton);

        //Window
        frame = new JFrame("Calculator");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(355,550);
        frame.setTitle("Calculator (2 Numbers)");
        frame.getContentPane().setBackground(Color.darkGray);
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);

        //this.add(resultsPanel);
        frame.add(buttonPanel);
        frame.add(textField);
        frame.add(finalPanel);

        //Last
        frame.setVisible(true);
    } // end of Calc method

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    } // end of main

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0;i<10;i++){ //detect when a number is pressed
            if(e.getSource()==numberButtons[i]){
                textField.setText(textField.getText().concat(String.valueOf(i)));
                numberDeque.addLast(String.valueOf(i));
                firstIsEmpty = false;
            }
        }//END of detect when a number is pressed


        if(e.getSource()==pointButton){
            if(!numberDeque.contains(".") && !(numberDeque.isEmpty())){
                textField.setText(textField.getText().concat("."));
                numberDeque.addLast(".");}
        }

        if(e.getSource()==plusButton){
            if(!firstIsEmpty && !hasBeenPressed){
                if(isFirstCalculation){num1 = stringToDouble(numberDeque);}
                textField.setText(textField.getText().concat("+"));
                symbol='+';
                numberDeque.clear();
                hasBeenPressed = true;
                secondIsEmpty = false;
                }
            }
        if(e.getSource()==minusButton){
            if(!firstIsEmpty && !hasBeenPressed){
                if(isFirstCalculation){num1 = stringToDouble(numberDeque);}
                textField.setText(textField.getText().concat("-"));
                symbol='-';
                numberDeque.clear();
                hasBeenPressed = true;
                secondIsEmpty = false;}
            }
        if(e.getSource()==multButton){
            if(!firstIsEmpty && !hasBeenPressed){
                if(isFirstCalculation){num1 = stringToDouble(numberDeque);}
                textField.setText(textField.getText().concat("*"));
                symbol='*';
                numberDeque.clear();
                hasBeenPressed=true;
                secondIsEmpty = false;}
           }
        if(e.getSource()==divButton && !hasBeenPressed){
            if(!firstIsEmpty){
                if(isFirstCalculation){num1 = stringToDouble(numberDeque);}
                textField.setText(textField.getText().concat("/"));
                symbol='/';
                numberDeque.clear();
                hasBeenPressed=true;
                secondIsEmpty = false;}
            }
        if(e.getSource()==clearButton){
            textField.setText("");
            numberDeque.clear();
            if(!firstIsEmpty){
                symbol = '@';
                firstIsEmpty = true;
                secondIsEmpty = true;
                isFirstCalculation = true;
                hasBeenPressed = false;
                numberDeque.clear();
            }
        }
        if(e.getSource()==erraseButton){
           String text = textField.getText();
           textField.setText("");
           for (int i=0; i<text.length()-1;i++){
               textField.setText(textField.getText()+text.charAt(i));
               if(text.charAt(i) == '+'||text.charAt(i) == '-'||text.charAt(i) == '*'||text.charAt(i) == '/'){
                   symbol = '@';
                   hasBeenPressed=false;
                   secondIsEmpty=true;
               }
           }
            if(text.length() == 1){
                numberDeque.clear();
                isFirstCalculation = true;
                hasBeenPressed = false;
                firstIsEmpty = true;
                secondIsEmpty = true;
            }

        }


        if(e.getSource()==equalButton){
            if(!firstIsEmpty && hasBeenPressed && !secondIsEmpty){
                num2 = stringToDouble(numberDeque);
                //symbol='=';
                switch (symbol){
                    case '+':
                        result=num1+num2;
                        break;
                    case '-':
                        result=num1-num2;
                        break;
                    case '*':
                        result=num1*num2;
                        break;
                    case '/':
                        result=num1/num2;
                        break;
                    case '@':
                        //numberDeque.clear();
                        break;
                }
                textField.setText(String.valueOf(result));
                num1=result;
                isFirstCalculation = false;
                hasBeenPressed = false;
                secondIsEmpty = true;
            }
        }



    } // end of actionPerformed

} // end of Calculator Class

