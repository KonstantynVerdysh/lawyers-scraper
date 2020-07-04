package com.ua.verdysh.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LawyerProfile {
    private String url;
    private String fullName;
    private String jobPosition;
    private String description;
    private String photo;
    private String mail;

    @Override
    public String toString() {
        return url + "\n" + fullName + "\n" + jobPosition + "\n" + description + "\n" + photo + "\n" + mail + "\n";
    }
}
