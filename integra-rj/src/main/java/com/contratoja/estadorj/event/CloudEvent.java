package com.contratoja.estadorj.event;

import lombok.*;

import java.time.LocalDateTime;

@lombok.Data
@NoArgsConstructor
public class CloudEvent {
    @NonNull
    private String id;
    @NonNull
    private LocalDateTime time;
    @NonNull
    private String type;
    @NonNull
    private String source;
    @NonNull
    private String specversion;
    private String datacontenttype;
    @NonNull
    private Data data;
}
