package me.gqz.mapper;

import me.gqz.config.MyMapper;
import me.gqz.domain.GqzAppVersion;

import java.util.List;

/**
 * <p>Title: GqzAppVersionMapper. </p>
 * @author dragon
 * @date 2018/7/16 下午3:26
 */
public interface GqzAppVersionMapper extends MyMapper<GqzAppVersion> {
    /**
     * <p>Title: getVersionInfo. </p>
     * <p>获取高秋梓资源站上线版本信息 </p>
     * @author dragon
     * @date 2018/7/16 下午3:34
     * @return List<GqzAppVersion>
     */
    List<GqzAppVersion> getVersionInfo();
}