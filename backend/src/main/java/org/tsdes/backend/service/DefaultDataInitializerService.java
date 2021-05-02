package org.tsdes.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.function.Supplier;

/**
 * Created by arcuri82 on 15-Dec-17.
 */
@Service
public class DefaultDataInitializerService {

   /* @Autowired
    private CategoryService categoryService;*/

    @Autowired
    private UserService userService;

   /* @Autowired
    private TripService tripService;*/

    @PostConstruct
    public void initialize(){

        attempt(() -> userService.createUser("foo", "foo", "bar", "foo@bar.com", "123"));
        /*attempt(() -> tripService.createTrip("Paris", "Fly to paris", 4999, "France", LocalDate.of(2022, Month.JANUARY, 13)));
        attempt(() -> tripService.createTrip("Milano", "Fly to milano", 2599, "Italy", LocalDate.of(2022, Month.FEBRUARY, 12)));
        attempt(() -> tripService.createTrip("Cos", "Fly to cos", 1299, "Greece", LocalDate.of(2023, Month.MARCH, 11)));
        attempt(() -> tripService.createTrip("Nice", "Fly to nice", 1999, "France", LocalDate.of(2022, Month.APRIL, 10)));
        attempt(() -> tripService.createTrip("Sweden", "Fly to sweden", 6049, "Sweden", LocalDate.of(2022, Month.MAY, 9)));
        attempt(() -> tripService.createTrip("Rhodes", "Fly rhodes", 2099, "Greece", LocalDate.of(2022, Month.JUNE, 8)));
*/
        /*Long ctgSE = attempt(() -> categoryService.createCategory("Software Engineering"));
        Long ctgH = attempt(() -> categoryService.createCategory("History"));

        Long sEP = attempt(() -> categoryService.createSubCategory(ctgSE, "Enterprise Programming"));
        Long sIS = attempt(() -> categoryService.createSubCategory(ctgSE, "Information Security"));
        Long sJ = attempt(() -> categoryService.createSubCategory(ctgSE, "Java"));
        Long sA = attempt(() -> categoryService.createSubCategory(ctgSE, "Algorithms and Data Structure"));

        Long sRE = attempt(() -> categoryService.createSubCategory(ctgH, "Roman Empire"));

        createEnterpriseQuestions(sEP);
        createJavaQuestions(sJ);
        createSecurityQuestions(sIS);
        createAlgorithmQuestions(sA);
        createRomeEmpireQuestions(sRE);*/
    }

    /*private void createEnterpriseQuestions(Long sub) {

        createQuiz(
                sub,
                "What does JPA stands for?",
                "Java Pale Ale",
                "Java Persistence API",
                "Java Process Analyzer",
                "Java Persistence Analyzer",
                1);

        createQuiz(
                sub,
                "What does JEE stands for?",
                "Java Embedded Edition",
                "Java Extended Edition",
                "Java Enterprise Edition",
                "Java Excelsior Edition",
                2);
        createQuiz(
                sub,
                "Which of these is a JPA implementation?",
                "Hibernate",
                "Wildfly",
                "Glassfish",
                "Jackson",
                0);
    }

    private void createJavaQuestions(Long sub){

        createQuiz(
                sub,
                "What is a 'volatile' variable?",
                "Java does not have volatile variables",
                "A variable whose value cannot be changed",
                "A variable whose value might change every time it is read",
                "A variable whose value is never cached",
                3);
        createQuiz(
                sub,
                "What is a 'final' variable?",
                "A variable that can be used only when the JVM is shutting down",
                "A variable whose value cannot be changed once initialized",
                "A variable whose value might change every time it is read",
                "Java does not have final variables",
                1);
    }

    private void createSecurityQuestions(Long sub) {
        createQuiz(
                sub,
                "Why should hashed passwords be 'salted'?",
                "You cannot 'salt' a password",
                "It makes the passwords easier to remember",
                "Help to defend from dictionary attacks",
                "They taste better",
                2);

        createQuiz(
                sub,
                "Which grade will you get if you submit a project that is vulnerable to SQL Injection attacks?",
                "One grade less (e.g., a B turns into a C)",
                "Two grades less (e.g., a B turns into a D)",
                "An A, because so your lecturer can have fun hacking your web site",
                "A straight F, regardless of what done in the rest of the project",
                3);

    }

    private void createAlgorithmQuestions(Long sub) {
        createQuiz(
                sub,
                "What best describe the runtime complexity of binary search?",
                "5n",
                "O(n)",
                "O(log n)",
                "O(n log n)",
                2);

        createQuiz(
                sub,
                "What can you say about sorting algorithms?",
                "Merge Sort is strictly better than Quick Sort",
                "Quick Sort is strictly better than Merge Sort",
                "Bubble Sort is better than Merge/Quick Sort, as it uses less space",
                "Merge Sort is asymptotically optimal in the number of comparisons",
                3);

    }

    private void createRomeEmpireQuestions(Long sub) {
        createQuiz(
                sub,
                "Who was the first Roman Emperor?",
                "Caligula",
                "Tiberius",
                "Augustus",
                "Caesar",
                2);

        createQuiz(
                sub,
                "After which god is the month 'Mars' named?",
                "God of Thunders",
                "God of Love",
                "God of Wars",
                "God of Pestilence",
                2);

        createQuiz(
                sub,
                "According to the legend, who founded Rome ?",
                "Romulus and Remus",
                "Augustus and Caesar",
                "Tiberius and Claudius",
                "Erik and Olav",
                0);

        createQuiz(
                sub,
                "Which was the largest empire in history?",
                "Mongol Empire",
                "Russian Empire",
                "English Empire",
                "Roman Empire",
                2);

        createQuiz(
                sub,
                "Who were the Praetorians?",
                "Priests of the God Pratunus",
                "Slaves",
                "Barbarians",
                "Elite soldiers",
                3);
    }
*/


    /*private void createQuiz(
            Long subCategoryId,
            String question,
            String firstAnswer,
            String secondAnswer,
            String thirdAnswer,
            String fourthAnswer,
            int indexOfCorrectAnswer){
        attempt(() -> quizService.createQuiz(
                subCategoryId,
                question,
                firstAnswer,
                secondAnswer,
                thirdAnswer,
                fourthAnswer,
                indexOfCorrectAnswer));
    }
*/
    private  <T> T attempt(Supplier<T> lambda){
        try{
            return lambda.get();
        }catch (Exception e){
            return null;
        }
    }
}
