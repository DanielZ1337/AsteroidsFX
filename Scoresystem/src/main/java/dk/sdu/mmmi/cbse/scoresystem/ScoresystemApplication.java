package dk.sdu.mmmi.cbse.scoresystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ScoresystemApplication {

	private static Long totalScore = 0L;

	public static void main(String[] args) {
		SpringApplication.run(ScoresystemApplication.class, args);
	}

	@GetMapping("/score")
	public Long calculateScore(@RequestParam(value = "score") Long score) {
		totalScore += score;
		return totalScore;
	}

}
