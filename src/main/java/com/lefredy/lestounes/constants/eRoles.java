package com.lefredy.lestounes.constants;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public enum eRoles implements GrantedAuthority {
    ADMIN("ADMIN"),
    USER("USER");


    private final String label;

    eRoles(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public @Nullable String getAuthority() {
        return label;
    }
}
