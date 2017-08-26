class FrequencyTableBuilder implements Comparable<FrequencyTableBuilder> {
	int value;
	String code = "";
	int frequency = 0;

	public FrequencyTableBuilder(int value) {
		this.value = value;
		this.frequency = 0;
		// System.out.println("from frequency model1"+ this.value);
	}

	public FrequencyTableBuilder(int value, int frequency) {
		this.value = value;
		this.frequency = 0;
		// System.out.println("from frequency model2"+ this.value);
	}

	public FrequencyTableBuilder() {
		// System.out.println("from frequency model3"+ this.value);
	}

	public void updateFrequency() {
		// System.out.println("from update"+ this.frequency);
		this.frequency++;
		// System.out.println("updated frequency"+ this.frequency);
	}

	public int getFrequency() {
		return this.frequency;
	}

	public int getElementIndex() {
		// System.out.println("from get element index"+ this.value);
		return this.value;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int compareTo(FrequencyTableBuilder o) {
		// TODO Auto-generated method stub
		final int BEFORE = 1;
		final int EQUAL = 0;
		final int AFTER = -1;
		if (this.frequency == o.frequency) {
			return EQUAL;
		}
		if (this.frequency < o.frequency) {
			return BEFORE;
		}
		if (this.frequency > o.frequency) {
			return AFTER;
		}
		return 0;
	}

}
