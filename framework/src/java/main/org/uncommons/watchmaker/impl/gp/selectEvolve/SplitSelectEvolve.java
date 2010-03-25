package org.uncommons.watchmaker.impl.gp.selectEvolve;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;

/**
 * <p>Compound select evolve pipeline that allows the evolution of a population
 * to be split into two separate streams.  A percentage of the population
 * will be evolved according to one specified selection / operator pipeline 
 * and the remainder according to another selection / operator pipeline.
 * When both streams have been executed, the resulting offspring will be returned 
 * as a single combined population.</p>
 *
 * <p>This kind of separation is common in a genetic programming context where,
 * for example, 10% of the population is mutated and the remaining 90%
 * undergoes cross-over independently.</p>
 *
 * <p>To split evolution into more than two streams, multiple SplitEvolution operators
 * can be combined.  By combining SplitEvolution operators with
 * {@link org.uncommons.watchmaker.framework.operators.EvolutionPipeline} operators, elaborate evolutionary schemes can be
 * constructed.</p>
 *
 * @param <T> The type of evolved entity dealt with by this operator.
 * @author Peter Day
 *
 */
public class SplitSelectEvolve<T> implements SelectEvolvePipeline<T> {

	
	private final SelectEvolvePipeline<T> pipeline1;
    private final SelectEvolvePipeline<T> pipeline2;
    private final NumberGenerator<Double> weightVariable;
	
    /**
     * @param pipeline1 the pipeline to apply to the first part of the population
     * @param pipeline2 the pipeline to apply to the second part of the population
     * @param weight the ratio between the two evolutionary pipelines
     */
    public SplitSelectEvolve(SelectEvolvePipeline<T> pipeline1, SelectEvolvePipeline<T> pipeline2, NumberGenerator<Double> weight) {
		this.pipeline1 = pipeline1;
		this.pipeline2 = pipeline2;
		this.weightVariable = weight;
	}
    
    public SplitSelectEvolve(SelectEvolvePipeline<T> pipeline1, SelectEvolvePipeline<T> pipeline2, double weight) {
    	this(pipeline1, pipeline2, new ConstantGenerator<Double>(weight));
        if (weight <= 0 || weight >= 1)
        {
            throw new IllegalArgumentException("Split ratio must be greater than 0 and less than 1.");
        }
	}
    
    
	@Override
	public List<T> selectEvolve(List<EvaluatedCandidate<T>> population,
			boolean naturalFitnessScores, int requiredChildren, Random rng) {
		
		double ratio = weightVariable.nextValue();
        int size = (int) Math.round(ratio * requiredChildren);
        List<T> result = new ArrayList<T>(requiredChildren);
        result.addAll(this.pipeline1.selectEvolve(population, naturalFitnessScores, size, rng));
        result.addAll(this.pipeline2.selectEvolve(population, naturalFitnessScores, requiredChildren - size, rng));
        return result;
	
	}

}
