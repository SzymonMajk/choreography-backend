package net.choreography;

import net.choreography.model.Choreography;
import net.choreography.model.DirectedFigure;
import net.choreography.model.Figure;
import net.choreography.model.FigureRepository;

import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the choreography, where we work together on connecting dance figures on dance floor in time.");
        printSlowWaltz();
        System.out.println("Thank you for using choreography, hope to see you doing same on dance floor!");
    }

    private static void printSlowWaltz() {
        System.out.println("Lets start with quick demo on our slow waltz!");

        FigureRepository figureRepository = new FigureRepository();
        DirectedFigure preparationStep = figureRepository.getWaltzPreparationStep();
        List<Figure> waltzSteps = figureRepository.getWaltzFigures().stream().toList();

        Choreography choreography = new Choreography(preparationStep, figureRepository.getWaltzFigures());
        choreography.connect(waltzSteps.get(1));

        ListIterator<DirectedFigure> choreographyIterator = choreography.getChoreographyIterator();
        while (choreographyIterator.hasNext()) {
            System.out.println(choreographyIterator.next());
        }
    }
}