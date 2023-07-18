package org.coursesandsandbox.objectflatterer;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ResultCategory {

    private String id;

    private String idPath;

    private String lang;

    private String namePath;
}
