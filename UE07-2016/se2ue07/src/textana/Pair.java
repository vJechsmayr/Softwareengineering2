package textana;

public class Pair<A, B> {

	public final A fst;
	public final B scd;

	public Pair(A fst, B scd) {
		super();
		this.fst = fst;
		this.scd = scd;
	}

	@Override
	public String toString() {
		return "(" + fst + ", " + scd + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fst == null) ? 0 : fst.hashCode());
		result = prime * result + ((scd == null) ? 0 : scd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<?, ?> other = (Pair<?, ?>) obj;
		if (fst == null) {
			if (other.fst != null)
				return false;
		} else if (!fst.equals(other.fst))
			return false;
		if (scd == null) {
			if (other.scd != null)
				return false;
		} else if (!scd.equals(other.scd))
			return false;
		return true;
	}

}
