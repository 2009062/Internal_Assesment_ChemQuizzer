package com.example.internalassesmentchemquzier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class GameController {

    public Label questionLbl;
    public Label AnsOneLbl;
    public Label AnsTwoLbl;
    public Label AnsThreeLbl;
    public Label hintOneLbl;
    public Label hintTwoLbl;
    public Label timer;

    public ObservableList<Element> elements = FXCollections.observableArrayList();
    int currentQuestion = 0;

    public void initialize() throws IOException {
        //Create 10 random questions using data from the periodic table.

        createQuizQuestions(10);

        //questions.add(new Question(,,,));
      //  saveBtn();

        //Load question
        loadQuestion(currentQuestion);
        currentQuestion++;
        //start the timer
        /** TO DO **/

    }

    public void saveBtn() throws IOException {

        Gson gson = new GsonBuilder() .setPrettyPrinting() . create();
        try(FileWriter writer = new FileWriter("test.json")){
            gson. toJson(elements , writer);
            System.out.println("Saved!");
        } catch (IOException e) {
            e. printStackTrace() ;
        }
    }
    //mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/

    private void createQuizQuestions(int numQuestions) throws FileNotFoundException {
        loadElements();
        System.out.println(elements);
    }

    private void loadQuestion(int i) {

        //                              the element          the question(should be random)
        Random rand = new Random();
        int qNum = rand.nextInt(elements.get(i).getQuestions().size());
        questionLbl.setText(elements.get(i).getQuestions().get(qNum).getQuestion());
        AnsOneLbl.setText(elements.get(i).getQuestions().get(qNum).getAnsOne());
        AnsTwoLbl.setText(elements.get(i).getQuestions().get(qNum).getOptionY());
        AnsThreeLbl.setText(elements.get(i).getQuestions().get(qNum).getOptionZ());
        hintOneLbl.setText(elements.get(i).getQuestions().get(qNum).hintOne);
        hintOneLbl.setText(elements.get(i).getQuestions().get(qNum).hintOne);

    }


    //mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
    private void loadElements() throws FileNotFoundException {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("elements.json")) {
            Type listType = new TypeToken<ArrayList<Element>>(){}.getType();
            ArrayList<Element> imports = gson.fromJson(reader, listType);
            elements = FXCollections.observableArrayList(imports);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aBtn(ActionEvent actionEvent) {
    }

    public void bBtn(ActionEvent actionEvent) {
    }

    public void cBtn(ActionEvent actionEvent) {
    }

    public void menuButtonAction(ActionEvent actionEvent) throws IOException {
        HelloApplication.setRoot("menu-view");
    }
}