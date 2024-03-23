package com.bntech.googleintegrator.manager.data.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShorthandLink {
    @Id
    private String id;
    private String shortLink;
    private String fullLink;
}
