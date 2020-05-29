package br.com.codenation.domain;

import java.util.List;

public interface TimeRepository {

    public Time ofId(Long timeId);

    public void add(Time time);

    public Time[] all();
}