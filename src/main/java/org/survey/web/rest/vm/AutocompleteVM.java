package org.survey.web.rest.vm;

import java.util.List;

public class AutocompleteVM<T> {
    List<T> results;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public static <V>AutocompleteVM<V> create(List<V> values) {
        AutocompleteVM<V> auto = new AutocompleteVM<>();
        auto.setResults(values);
        return auto;
    }
}
