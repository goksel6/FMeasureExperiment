package main;

import com.github.habernal.confusionmatrix.ConfusionMatrix;

public class FMeasureExperiment {

	public static void main(String[] args) {
		ConfusionMatrix cm = new ConfusionMatrix();

		cm.increaseValue("neg", "neg", 25);
		cm.increaseValue("neg", "neu", 5);
		cm.increaseValue("neg", "pos", 2);
		cm.increaseValue("neu", "neg", 3);
		cm.increaseValue("neu", "neu", 32);
		cm.increaseValue("neu", "pos", 4);
		cm.increaseValue("pos", "neg", 1);
		cm.increaseValue("pos", "pos", 15);

		System.out.println(cm);
		System.out.println(cm.printLabelPrecRecFm());
		System.out.println(cm.getPrecisionForLabels());
		System.out.println(cm.getRecallForLabels());
		System.out.println(cm.printNiceResults());
	}

}
