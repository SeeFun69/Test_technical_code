package com.example.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class segitiga {

    @GetMapping("/segitiga/{angka}")
    public ResponseEntity<String> buatSegitiga(@PathVariable("angka") String angka) {
        // Validasi input angka
        if (!angka.matches("[0-9]+")) {
            return new ResponseEntity<>("Input tidak valid. Masukkan angka saja.", HttpStatus.BAD_REQUEST);
        }

        // Konversi string ke array integer
        int[] arrNum = new int[angka.length()];
        for (int i = 0; i < angka.length(); i++) {
            arrNum[i] = Integer.parseInt(Character.toString(angka.charAt(i)));
        }

        // Membentuk segitiga
        int n = arrNum.length;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int maxDigits = i;
            for (int j = 1; j <= i; j++) {
                if (count >= n) {
                    sb.append("0");
                } else {
                    sb.append(arrNum[count]);
                    count++;
                }
            }
            // Tambahkan digit "0" pada akhir digit jika jumlah digit kurang dari digit maksimum
            while (sb.length() < maxDigits) {
                sb.append("0");
            }
            sb.append("\n");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }

    @GetMapping("/bilangan-ganjil/{max}")
    public List<Integer> generateBilanganGanjil(@PathVariable("max") int max) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            if (i % 2 == 1) {
                result.add(i);
            }
        }
        return result;
    }

    @GetMapping("/bilanganprima/{max}")
    public ResponseEntity<String> generateBilanganPrima(@PathVariable("max") int max) {
        if (max < 2) {
            return new ResponseEntity<>("Masukkan angka maksimal minimal 2", HttpStatus.BAD_REQUEST);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= max; i++) {
            if (isPrima(i)) {
                sb.append(i).append("\n");
            }
        }

        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }

    private boolean isPrima(int num) {
        if (num <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
