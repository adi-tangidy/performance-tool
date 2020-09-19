package com.abb.es.df.perf.util;

import lombok.Builder;
import lombok.Data;

import java.util.IntSummaryStatistics;

@Builder
@Data
public class SummaryDTO {

    private String identifier;
    private IntSummaryStatistics statistics;

}
