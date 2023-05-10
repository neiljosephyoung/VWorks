package com.example.VWorks.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@Service
public class AlgorithmService {

    public ResponseEntity mergeSort(String body) {
        try {
            JSONObject jsonBodyObject = new JSONObject(body);
            if(!jsonBodyObject.has("array")){
                //no array data found created random array
                int[] generatedArray = new int[100];
                Random random = new Random();
                for (int i = 0; i < generatedArray.length; i++) {
                    generatedArray[i] = random.nextInt();
                }

                Instant start = Instant.now();
                testMergeSort(generatedArray);
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);

                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Array sorted successfully",
                        "sortedArray", generatedArray,
                        "timeTaken", timeElapsed.toMillis() + "ms"
                ));

            }else{
                JSONArray jsonArray = jsonBodyObject.getJSONArray("array");
                int[] array = new int[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    array[i] = jsonArray.getInt(i);
                }

                Instant start = Instant.now();
                testMergeSort(array);
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);

                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Array sorted successfully",
                        "sortedArray", array,
                        "timeTaken", timeElapsed.toMillis() + "ms"
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(Map.of(
                "status", "Error",
                "message", "Array cannot be sorted",
                "sortedArray", "[]",
                "timeTaken", "N/A"
        ));

    }
    private void testMergeSort(int[] array) {
        int length = array.length;
        if (length <= 1) return; //ie array is in its smallest form

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0; //left
        int j = 0; //right
        for ( ; i < length; i++){
            if(i < middle){
                leftArray[i] = array[i];
            }else{
                rightArray[j] = array[i];
                j++;
            }
        }
        testMergeSort(leftArray);
        testMergeSort(rightArray);
        merge(array, leftArray, rightArray);
    }
    private void merge(int[] array, int[] leftArray, int[] rightArray){
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0;
        int l = 0;
        int r = 0;

        //within bounds
        while(l < leftSize && r < rightSize){
            //check if left value is less thus sorted
            if(leftArray[l] < rightArray[r]){
                array[i] = leftArray[l];
                i++;
                l++;
            }else{
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        //fill up the array with last value as there's nothing to compare
        while(l < leftSize){
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while(r < rightSize){
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }

    public ResponseEntity bubbleSort(String body){
        try {
            JSONObject jsonBodyObject = new JSONObject(body);
            if (!jsonBodyObject.has("array")) {
                //no array data found created random array
                int[] generatedArray = new int[100];
                Random random = new Random();
                for (int i = 0; i < generatedArray.length; i++) {
                    generatedArray[i] = random.nextInt();
                }

                Instant start = Instant.now();
                testBubbleSort(generatedArray, generatedArray.length);
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);

                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Array sorted successfully",
                        "sortedArray", generatedArray,
                        "timeTaken", timeElapsed.toMillis() + "ms"
                ));

                } else {
                    JSONArray jsonArray = jsonBodyObject.getJSONArray("array");
                    int[] array = new int[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        array[i] = jsonArray.getInt(i);
                    }

                    Instant start = Instant.now();
                    testBubbleSort(array, array.length);
                    Instant end = Instant.now();
                    Duration timeElapsed = Duration.between(start, end);

                    return ResponseEntity.ok(Map.of(
                            "status", "success",
                            "message", "Array sorted successfully",
                            "sortedArray", array,
                            "timeTaken", timeElapsed.toMillis() + "ms"
                    ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(Map.of(
                "status", "Error",
                "message", "Array cannot be sorted",
                "sortedArray", "[]",
                "timeTaken", "N/A"
        ));
    }
    private void testBubbleSort(int[] array, int arrLen){
        if(arrLen == 1) return;
        int rightEnd = array.length - 1;
        for (int i =0; i < rightEnd; i++){
            //check current index aganist index + 1 and swap if left is greater than right
            if(array[i] > array[i+1]){
                int temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
            }
        }

        testBubbleSort(array, arrLen -1);
    }

    public ResponseEntity quickSort(String body){
        try {
            JSONObject jsonBodyObject = new JSONObject(body);
            if (!jsonBodyObject.has("array")) {
                //no array data found created random array
                int[] generatedArray = new int[100];
                Random random = new Random();
                for (int i = 0; i < generatedArray.length; i++) {
                    generatedArray[i] = random.nextInt();
                }

                Instant start = Instant.now();
                Arrays.sort(generatedArray);
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);

                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Array sorted successfully",
                        "sortedArray", generatedArray,
                        "timeTaken", timeElapsed.toMillis() + "ms"
                ));

            } else {
                JSONArray jsonArray = jsonBodyObject.getJSONArray("array");
                int[] array = new int[jsonArray.length()];
                for (int i = 0; i < jsonArray.length(); i++) {
                    array[i] = jsonArray.getInt(i);
                }

                Instant start = Instant.now();
                Arrays.sort(array);
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);

                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Array sorted successfully",
                        "sortedArray", array,
                        "timeTaken", timeElapsed.toMillis() + "ms"
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(Map.of(
                    "status", "Error",
                    "message", "An Error Has Occured :"+ e.getMessage()
            ));
        }
    }
}
