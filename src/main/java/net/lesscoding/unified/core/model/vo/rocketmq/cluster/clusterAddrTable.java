package net.lesscoding.unified.core.model.vo.rocketmq.cluster;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class clusterAddrTable {
    private Map<String, List<String>> clusterArr;
}