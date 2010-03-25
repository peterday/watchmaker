package org.uncommons.watchmaker.impl.gp.selection;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.SelectionStrategy;

import java.util.*;

/**
 * Tournament selection strategy in which N individuals are selected at random and
 * the fittest is selected.
 * This is a trivial extension of the TournamentSelection genetic operator.
 * In order to remain compatible with the TournamentSelectionOperator a fitness Proportionate
 * probability can also be specified.
 *
 * @author Peter Day
 */
public class NTournamentSelection implements SelectionStrategy<Object> {

    private NumberGenerator<Integer> tournamentSize;
    Probability fitnessProportionateSelection = Probability.ONE;

    public NTournamentSelection(NumberGenerator<Integer> tournamentSize, Probability fitnessProportionateSelection) {
        this.tournamentSize = tournamentSize;
        this.fitnessProportionateSelection = fitnessProportionateSelection;
    }

    public NTournamentSelection(int tournamentSize, double probability) {
        this(new ConstantGenerator(tournamentSize), new Probability(probability));
    }

     public NTournamentSelection(int tournamentSize) {
        this(new ConstantGenerator(tournamentSize), Probability.ONE);
    }

      public NTournamentSelection(NumberGenerator<Integer> tournamentSize) {
        this(tournamentSize, Probability.ONE);
    }

    public <S extends Object> List<S> select(List<EvaluatedCandidate<S>> population, boolean naturalFitnessScores,
                                             int selectionSize, Random rng) {
        
        List<S> newPop = new ArrayList<S>();
        for (int i = 0; i<selectionSize; i++) {
            newPop.add(selectOne(population, naturalFitnessScores, rng));
        }

        return newPop;
    }

    public <S extends Object> S selectOne(List<EvaluatedCandidate<S>> population,
                                                              boolean naturalFitnessScores, Random rng) {

        int noInds = tournamentSize.nextValue();
        List<EvaluatedCandidate<S>> contestants = new ArrayList<EvaluatedCandidate<S>>(noInds);
        for (int i = 0; i<noInds; i++) {
            contestants.add(population.get(rng.nextInt()));
        }

        //sort to assure they are ranked according to fitness
        Collections.sort(contestants );

        //lowest fitness score will be first after sorting
        if (naturalFitnessScores) {
            //reverse if scores are natural (higher = better)
            Collections.reverse(contestants);
        }

        Iterator<EvaluatedCandidate<S>> iter = contestants.iterator();
        while(iter.hasNext()) {
            EvaluatedCandidate<S> candidate = iter.next();
            if (fitnessProportionateSelection.nextEvent(rng)) {
                //select this ind
                return candidate.getCandidate();
            }
        }
        //no individual selected!
         //return bottom of the pile (?!) in order to comply with
        //previous implementation
         return contestants.get(contestants.size()).getCandidate();

    }

}
