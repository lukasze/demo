package com.example.demo.repository;

import com.example.demo.model.Hobbit;
import org.springframework.data.jpa.repository.JpaRepository;
/*
    Używamy bardziej elastycznej opcji wtedy, gdy to niezbędne
    - rozszerzamy istniejący interfejs
    - dodajemy własne metody (nazwa metody nie jest trywialna!)
    - implementujemy własne repozytorium i 'miksujemy je z interfejsem'
    
 */
public interface HobbitRepository extends JpaRepository<Hobbit,Long> {
}
