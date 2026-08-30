package com.venkat.backendproject.Projections;

import lombok.Getter;

public interface ProductWithTitleAndId {
    // put getter method for corresponding attributes
    Long getId();
    String getTitle();

    String getDescription();
}
