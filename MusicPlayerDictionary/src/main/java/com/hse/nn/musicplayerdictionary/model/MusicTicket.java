package com.hse.nn.musicplayerdictionary.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "tickets")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MusicTicket {
    @Id
    private String ticket_no;
    @Field(type = FieldType.Text, name = "track_id")
    private String trackId;
    @Field(type = FieldType.Text, name = "track_title")
    private String trackTitle;
    @Field(type = FieldType.Text, name = "track_author")
    private String track_author;

    public String getTicket_no() {
        return ticket_no;
    }

    public void setTicket_no(String ticket_no) {
        this.ticket_no = ticket_no;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public String getTrack_author() {
        return track_author;
    }

    public void setTrack_author(String track_author) {
        this.track_author = track_author;
    }
}