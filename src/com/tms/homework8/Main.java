package com.tms.homework8;

import java.io.*;
import java.util.Scanner;

/**
 * Проверяет файл с номерами документов и распределяет их по двум файлам
 * rightDocNumbers.txt - содержит правильные номера,
 * wrongDocNumbers.txt - номера с количеством символов больше 15 или не начинающиеся на "docnum" или "kontract"
 * Файлы создаются в том же каталоге, где и исходный, заданный пользователем, файл с номерами документов
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Введите путь к файлу");
        Scanner userInput = new Scanner(System.in);
        String path = userInput.nextLine();

        File file = new File(path);

        if (file.exists()){

            FileReader docNum = null;
            FileWriter validDocNum = null;
            FileWriter invalidDocNum = null;
            String[] numbers;

            try {
                docNum = new FileReader(file);
                Scanner docNumScanner = new Scanner(docNum);
                validDocNum = new FileWriter(file.getParent() + "/rightDocNumbers.txt");
                invalidDocNum = new FileWriter(file.getParent() + "/wrongDocNumbers.txt");

                String tmp;

                while (docNumScanner.hasNextLine()) {
                    tmp = docNumScanner.nextLine();
                    if ((tmp.indexOf("docnum") == 0) || (tmp.indexOf("kontract") == 0)){
                        if (tmp.length() == 15){
                            validDocNum.write(tmp + "\n");
                        } else {
                            invalidDocNum.write(tmp + " - Номер документа содержит больше 15 символов\n");
                        }
                    } else {
                        if (tmp.length() > 15){
                            invalidDocNum.write(tmp + " - Номер документа не начинается с docnum или kontract и содержит больше 15 символов\n");
                        } else {
                            invalidDocNum.write(tmp + " - Номер документа не начинается с docnum или kontract\n");
                        }
                    }
                }

                System.out.println("Номера документов рассортированы и находятся в каталоге");
                System.out.println(file.getParent());

                docNumScanner.close();

            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
            } catch (IOException e) {
                System.out.println("Ошибка в записи файла");
            } finally {
                try {
                    docNum.close();
                    validDocNum.close();
                    invalidDocNum.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            System.out.println("Файла по этому пути не существует");
        }

        userInput.close();

    }
}
