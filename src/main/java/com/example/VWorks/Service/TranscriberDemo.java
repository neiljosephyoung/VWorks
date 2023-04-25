package com.example.VWorks.Service;

import java.io.InputStream;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.decoder.adaptation.Stats;
import edu.cmu.sphinx.decoder.adaptation.Transform;
import edu.cmu.sphinx.result.WordResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * A simple example that shows how to transcribe a continuous audio file that
 * has multiple utterances in it.
 */
public class TranscriberDemo {

    public static String transcribe(MultipartFile file)  {
        try {
            System.out.println("Loading models...");

            Configuration configuration = new Configuration();

            // Load model from the jar
            configuration
                    .setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");

            // You can also load model from folder
            // configuration.setAcousticModelPath("file:en-us");

            configuration
                    .setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            configuration
                    .setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

            StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(
                    configuration);

            InputStream stream = file.getInputStream();

//        InputStream stream = TranscriberDemo.class
//                .getResourceAsStream("/audio/thisIsaTest.wav");

            stream.skip(44);
            //.getResourceAsStream("/edu/cmu/sphinx/demo/aligner/10001-90210-01803.wav");

            // InputStream stream = getClass().getResourceAsStream("/audio/thisIsaTest.wav");

            // Simple recognition with generic model
            recognizer.startRecognition(stream);
            SpeechResult result;
            while ((result = recognizer.getResult()) != null) {

                System.out.format("Hypothesis: %s\n", result.getHypothesis());

                System.out.println("List of recognized words and their times:");
                for (WordResult r : result.getWords()) {
                    System.out.println(r);
                }

                System.out.println("Best 3 hypothesis:");
                for (String s : result.getNbest(3))
                    System.out.println(s);

            }
            recognizer.stopRecognition();

            // Live adaptation to speaker with speaker profiles

            stream = TranscriberDemo.class
                    .getResourceAsStream("/audio/thisIsaTest.wav");
            stream.skip(44);

            // Stats class is used to collect speaker-specific data
            Stats stats = recognizer.createStats(1);
            recognizer.startRecognition(stream);
            while ((result = recognizer.getResult()) != null) {
                stats.collect(result);
            }
            recognizer.stopRecognition();

            // Transform represents the speech profile
            Transform transform = stats.createTransform();
            recognizer.setTransform(transform);

            // Decode again with updated transform
            stream = TranscriberDemo.class
                    .getResourceAsStream("/audio/thisIsaTest.wav");
            stream.skip(44);
            recognizer.startRecognition(stream);
            StringBuilder resultString = new StringBuilder();
            while ((result = recognizer.getResult()) != null) {
                resultString.append(result.getHypothesis());
            }
            recognizer.stopRecognition();
            return resultString.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
