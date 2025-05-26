package net.lesscoding.unified.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lesscoding.unified.entity.MqPortDict;
import net.lesscoding.unified.mapper.MqPortDictMapper;
import net.lesscoding.unified.service.MqPortDictService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eleven
 * @date 2025/5/24 16:21
 * @apiNote
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MqPortDictServiceImpl implements MqPortDictService {

    private final MqPortDictMapper mqPortDictMapper;
    @Override
    public List<MqPortDict> all() {
        return mqPortDictMapper.selectList(null);
    }
}
