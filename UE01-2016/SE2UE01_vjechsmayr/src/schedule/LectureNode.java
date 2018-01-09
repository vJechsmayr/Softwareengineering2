package schedule;

/**
 * @author V.Jechsmayr
 * Datum: 04.03.2016
 */

public class LectureNode {

	Lecture lecture;
	LectureNode next;

	LectureNode(Lecture l, LectureNode next) {
		lecture = l;
		this.next = next;
	}

	LectureNode(Lecture l) {
		this(l, null);
	}
}
