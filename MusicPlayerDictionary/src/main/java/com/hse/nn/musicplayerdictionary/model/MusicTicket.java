package com.hse.nn.musicplayerdictionary.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "tickets")
@ToString
@Builder
public class MusicTicket {
    @Id
    private String ticket_no;
    @Field(type = FieldType.Text, name = "track_id")
    private String trackId;
    @Field(type = FieldType.Text, name = "track_title")
    private String trackTitle;
}