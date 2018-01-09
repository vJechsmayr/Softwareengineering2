package textana;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextAnalysisFns {

	// (a) find lines which contains a particular word

	public static Optional<String> findLine(String fileName, String word) throws IOException {		
		return Files.lines(Paths.get(fileName)).filter(s -> s.contains(word)).findFirst();
	}

	public static List<String> findLines(String fileName, String word) throws IOException {
		
		return Files.lines(Paths.get(fileName)).filter(s -> s.contains(word)).collect(Collectors.toList());
	}

	// (b) find all line numbers which contain a text and return them in a list

	public static Optional<Integer> findLineNum(String fileName, String word) throws IOException {
		
		AtomicInteger index = new AtomicInteger();
		
		return Files.lines(Paths.get(fileName)).map(s -> new Pair<String, Integer>(s,index.incrementAndGet())).filter(p->p.fst.contains(word)).map(p->p.scd).findFirst();
		
	}

	public static List<Integer> findLineNums(String fileName, String word) throws IOException {
		AtomicInteger index = new AtomicInteger();
		
		return Files.lines(Paths.get(fileName)).map(s -> new Pair<String, Integer>(s,index.incrementAndGet())).filter(p->p.fst.contains(word)).map(p->p.scd).collect(Collectors.toList());
	}

	// (c) all words

	public static List<String> words(String fileName) throws IOException {
		return Files.lines(Paths.get(fileName)).map(l->l.split("[ .,;?!.:()]")).flatMap(p-> Stream.of(p)).filter(s -> s.matches("[a-zA-Z]+$")).collect(Collectors.toList());
	}

	// (d) word occurrences

	public static Map<String, Integer> wordOccurrences(String fileName) throws IOException {
		return Files.lines(Paths.get(fileName)).map(l->l.split("[ .,;?!.:()]")).flatMap(p-> Stream.of(p)).filter(s -> s.matches("[a-zA-Z]+$")).map(s->s.toUpperCase()).collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
	}

	// (e) set of words

	public static Set<String> setOfWords(String fileName) throws IOException {
		Set<String> temp = Files.lines(Paths.get(fileName)).map(l->l.split("[ .,;?!.:()]")).flatMap(p-> Stream.of(p)).filter(s -> s.matches("[a-zA-Z]+$")).map(s->s.toUpperCase()).collect(Collectors.toSet());
		
		List<String> list = new ArrayList<String>(temp);
		list.sort((s1,s2) -> s1.compareTo(s2));
		
		return new TreeSet<String>(list);
	}

}
