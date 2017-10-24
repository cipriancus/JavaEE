package com.laborator.repository;

import com.laborator.model.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordRepository {
    private static List<Record> allRecords;
    private static RecordRepository recordRepository=null;

    protected RecordRepository() {
        allRecords = new ArrayList<Record>();
        allRecords.add(new Record("tomy","8",CategoryRepository.getInstance().getCategoryByName("Wirehair")));
    }

    public static RecordRepository getInstance(){
        if(recordRepository==null)
            recordRepository=new RecordRepository();
        return recordRepository;
    }

    public List<Record> getAllRecords() {
        return allRecords;
    }

    public Record getRecordById(int id) {
        for (Record iterator : allRecords) {
            if (iterator.getId().equals(id))
                return iterator;
        }
        return null;
    }

    public Record getRecordByName(String name, String category) {
        for (Record iterator : allRecords) {
            if (iterator.getName().equals(name) && iterator.getCategory().getBreed().equals(category))
                return iterator;
        }
        return null;
    }
    public boolean addRecord(Record record){

        for(Record iterator:allRecords){
            if(iterator.getName().equals(record.getName()))
                return false;
        }
        allRecords.add(record);
        return true;
    }
}
