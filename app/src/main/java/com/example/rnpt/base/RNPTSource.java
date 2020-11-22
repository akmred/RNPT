package com.example.rnpt.base;

import com.example.rnpt.base.dao.RnptDao;
import com.example.rnpt.base.model.RNPT;

import java.util.List;

// Вспомогательный класс, развязывающий
// зависимость между Room и RecyclerView
public class RNPTSource {

    private final RnptDao rnptDao;

    // Буфер с данными, сюда будем подкачивать данные из БД
    private List<RNPT> rnpts;

    public RNPTSource(RnptDao rnptDao) { this.rnptDao = rnptDao; }

    // Получить все РНПТ
    public List<RNPT> getRnpts(){
        // Если объекты еще не загруженны, то загружаем их
        // Сделано для того, чтобы не делать запросы в БД каждый раз
        if (rnpts == null){
            LoadRNPTs();
        }
        return rnpts;
    }

    private void LoadRNPTs() {
        rnpts = rnptDao.getAllRNPT();
    }

    // Получить количество записей
    public int getCountRnpts() {return  rnptDao.getCountRNPTs(); }

    // Добавить рнпт
    public void addRNPT(RNPT rnpt){
        long id = rnptDao.insertRNTP(rnpt);

        // После изменения БД надо перечитать буфер
        LoadRNPTs();
    }

}
