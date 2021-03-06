package hudson.plugins.jacoco;

import hudson.plugins.jacoco.model.Coverage;
import hudson.plugins.jacoco.model.CoverageElement.Type;

import java.io.Serializable;

/**
 * Holds the configuration details for {@link hudson.model.HealthReport} generation
 *
 * @author Stephen Connolly
 * @since 1.7
 */
public class JacocoHealthReportThresholds implements Serializable {
    private int minClass;
    private int maxClass;
    private int minMethod;
    private int maxMethod;
    private int minLine;
    private int maxLine;
    private int minBranch;
    private int maxBranch;
    private int minInstruction;
    private int maxInstruction;
    private int minComplexity;
    private int maxComplexity;

    public JacocoHealthReportThresholds() {
    }
    
    public JacocoHealthReportThresholds(
    		int minClass, int maxClass, int minMethod, int maxMethod, int minLine, int maxLine,
    		int minBranch, int maxBranch, int minInstruction, int maxInstruction, int minComplexity, int maxComplexity) {
        this.minClass = minClass;
        this.maxClass = maxClass;
        this.minMethod = minMethod;
        this.maxMethod = maxMethod;
        this.minLine = minLine;
        this.maxLine = maxLine;
		this.minBranch = minBranch;
		this.maxBranch = maxBranch;
		this.minInstruction = minInstruction;
		this.maxInstruction = maxInstruction;
		this.minComplexity = minComplexity;
		this.maxComplexity = maxComplexity;
        ensureValid();
    }

    private int applyRange(int min , int value, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public enum RESULT {BELLOWMINIMUM, BETWEENMINMAX, ABOVEMAXIMUM}
    
    public void ensureValid() {
        maxClass = applyRange(0, maxClass, 100);
        minClass = applyRange(0, minClass, maxClass);
        maxMethod = applyRange(0, maxMethod, 100);
        minMethod = applyRange(0, minMethod, maxMethod);
        maxLine = applyRange(0, maxLine, 100);
        minLine = applyRange(0, minLine, maxLine);
        maxBranch = applyRange(0, maxBranch, 100);
        minBranch = applyRange(0, minBranch, maxBranch);
        maxInstruction = applyRange(0, maxInstruction, 100);
        minInstruction = applyRange(0, minInstruction, maxInstruction);
        maxComplexity = applyRange(0, maxComplexity, 100);
        minComplexity = applyRange(0, minComplexity, maxComplexity);
    }

    public int getMinClass() {
        return minClass;
    }

    public void setMinClass(int minClass) {
        this.minClass = minClass;
    }

    public int getMaxClass() {
        return maxClass;
    }

    public void setMaxClass(int maxClass) {
        this.maxClass = maxClass;
    }

    public int getMinMethod() {
        return minMethod;
    }

    public void setMinMethod(int minMethod) {
        this.minMethod = minMethod;
    }

    public int getMaxMethod() {
        return maxMethod;
    }

    public void setMaxMethod(int maxMethod) {
        this.maxMethod = maxMethod;
    }

    public int getMinLine() {
        return minLine;
    }

    public void setMinLine(int minLine) {
        this.minLine = minLine;
    }

    public int getMaxLine() {
        return maxLine;
    }

    public void setMaxLine(int maxLine) {
        this.maxLine = maxLine;
    }

	public int getMinBranch() {
		return minBranch;
	}

	public int getMaxBranch() {
		return maxBranch;
	}

	public int getMinInstruction() {
		return minInstruction;
	}

	public int getMaxInstruction() {
		return maxInstruction;
	}

	public int getMinComplexity() {
		return minComplexity;
	}

	public int getMaxComplexity() {
		return maxComplexity;
	}

	public void setMinBranch(int minBranch) {
		this.minBranch = minBranch;
	}

	public void setMaxBranch(int maxBranch) {
		this.maxBranch = maxBranch;
	}

	public void setMinInstruction(int minInstruction) {
		this.minInstruction = minInstruction;
	}

	public void setMaxInstruction(int maxInstruction) {
		this.maxInstruction = maxInstruction;
	}

	public void setMinComplexity(int minComplexity) {
		this.minComplexity = minComplexity;
	}

	public void setMaxComplexity(int maxComplexity) {
		this.maxComplexity = maxComplexity;
	}

	public  RESULT getResultByTypeAndRatio(Coverage ratio) {
		    RESULT result = RESULT.ABOVEMAXIMUM;
		    Type covType = ratio.getType();
		    
			if (covType == Type.INSTRUCTION) {
				if (ratio.getPercentage() < minInstruction) {
					result = RESULT.BELLOWMINIMUM;
				} else if (ratio.getPercentage() < maxInstruction) {
					result = RESULT.BETWEENMINMAX;
				}
				
			} else if (covType == Type.BRANCH) {
				if (ratio.getPercentage() < minBranch) {
					result = RESULT.BELLOWMINIMUM;
				} else if (ratio.getPercentage() < maxBranch) {
					result = RESULT.BETWEENMINMAX;
				} 
			} else if (covType == Type.LINE) {
				if (ratio.getPercentage() < minLine) {
					result = RESULT.BELLOWMINIMUM;
				} else if (ratio.getPercentage() < maxLine) {
					result = RESULT.BETWEENMINMAX;
				} 
			} else if (covType == Type.COMPLEXITY) {
				if (ratio.getPercentage() < minComplexity) {
					result = RESULT.BELLOWMINIMUM;
				} else if (ratio.getPercentage() < maxComplexity) {
					result = RESULT.BETWEENMINMAX;
				} 
			} else if (covType == Type.METHOD) {
				if (ratio.getPercentage() < minMethod) {
					result = RESULT.BELLOWMINIMUM;
				} else if (ratio.getPercentage() < maxMethod) {
					result = RESULT.BETWEENMINMAX;
				} 
			} else if (covType == Type.CLASS) {
				if (ratio.getPercentage() < minClass) {
					result = RESULT.BELLOWMINIMUM;
				} else if (ratio.getPercentage() < maxClass) {
					result = RESULT.BETWEENMINMAX;
				} 
			}
			 
		return result;
	}
	@Override
	public String toString() {
		return "JacocoHealthReportThresholds [minClass=" + minClass
				+ ", maxClass=" + maxClass + ", minMethod=" + minMethod
				+ ", maxMethod=" + maxMethod + ", minLine=" + minLine
				+ ", maxLine=" + maxLine + ", minBranch=" + minBranch
				+ ", maxBranch=" + maxBranch + ", minInstruction="
				+ minInstruction + ", maxInstruction=" + maxInstruction
				+ ", minComplexity=" + minComplexity + ", maxComplexity="
				+ maxComplexity + "]";
	}
	

}
