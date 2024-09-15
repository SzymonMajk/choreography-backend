package net.choreography;

import net.choreography.model.Choreography;
import net.choreography.model.DirectedFigure;
import net.choreography.model.Figure;
import net.choreography.model.FigureRepository;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the choreography, where we work together on connecting dance figures on dance floor in time.");
        printSlowWaltz();
        System.out.println("Thank you for using choreography, hope to see you doing same on dance floor!");
    }

    private static void printSlowWaltz() {
        int finalDuration = 10;
        System.out.printf("Lets start with quick demo on our slow waltz basics for %s beats with prep\r\n", finalDuration);

        FigureRepository figureRepository = new FigureRepository();
        Map<String, Figure> waltzSteps = figureRepository.getWaltzFigures();

        Choreography choreography = new Choreography(waltzSteps);
        choreography.connect(waltzSteps.get("Natural Turn #1 part"));
        choreography.connect(waltzSteps.get("Natural Turn #2 part"));

        List<DirectedFigure> calculatedChoreography = choreography.calculateChoreography(finalDuration);
        ListIterator<DirectedFigure> choreographyIterator = calculatedChoreography.listIterator();
        while (choreographyIterator.hasNext()) {
            System.out.println(choreographyIterator.next());
        }
    }
}