package db;

import com.example.demo.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

public abstract class SimpleDataRepository<T extends Entity,ID extends Long> implements DataRepository<T,ID> { // 와일드 카드로 타입 제한
    private List<T> dataList = new ArrayList<>();
    private static long index = 0;
    private Comparator<T> sort = new Comparator<T>() {
        @Override
        public int compare(T o1, T o2) {
            return Long.compare(o1.getId(),o2.getId());
        }
    };

    // create // update 같이!

    @Override
    public T save(T data) {

        if (Objects.isNull(data)){
            throw new RuntimeException("Data is null");
        }

        // db에 데이터가 있는가?
        Optional<T> prevData = dataList.stream()
                .filter(it -> {
                    return it.getId().equals(data.getId());
                })
                .findFirst();

        if (prevData.isPresent()){
            // 기존 데이터가 있으면  -> update
            dataList.remove(prevData.get());
            dataList.add(data);
        }else {
            // 기존 데이터가 없는 경우 -> create
            index++;
            data.setId(index);
            dataList.add(data);
        }

        return data;
    }

    // read

    @Override
    public Optional<T> findById(ID id) {
        return dataList.stream()
                .filter(it ->{
                    return it.getId().equals(id);
                })
                .findFirst();
    }

    @Override
    public List<T> findAll() {
        return dataList.stream()
                .sorted(sort)
                .collect(Collectors.toList());
    }

    //delete


    @Override
    public void delete(ID id) {
        Optional<T> deleteEntity = dataList.stream()
                .filter(it -> {
                    return it.getId().equals(id);
                })
                .findFirst();

        if (deleteEntity.isPresent()){
            dataList.remove(deleteEntity.get());
        }
    }
}
