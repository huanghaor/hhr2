package com.hafiz.www.po;

import java.util.List;

/**
 * 主页页面加载实体
 */
public class InitIndexDataEntity {
    private List<EssayDiary> essayDiaries;

    private List<TechnologyModule> technologyModules;

    private List<MessageWal> messageWals;

    public List <MessageWal> getMessageWals() {
        return messageWals;
    }

    public List<TravelCollection> travelCollections;

    public List<IndustryInformation> industryInformations;

    public List <IndustryInformation> getIndustryInformations() {
        return industryInformations;
    }

    public void setIndustryInformations(List <IndustryInformation> industryInformations) {
        this.industryInformations = industryInformations;
    }

    public List <TravelCollection> getTravelCollections() {
        return travelCollections;
    }

    public void setTravelCollections(List <TravelCollection> travelCollections) {
        this.travelCollections = travelCollections;
    }

    public void setMessageWals(List <MessageWal> messageWals) {
        this.messageWals = messageWals;
    }

    public List <TechnologyModule> getTechnologyModules() {
        return technologyModules;
    }



    public void setTechnologyModules(List <TechnologyModule> technologyModules) {
        this.technologyModules = technologyModules;
    }

    public List <EssayDiary> getEssayDiaries() {
        return essayDiaries;
    }

    public void setEssayDiaries(List <EssayDiary> essayDiaries) {
        this.essayDiaries = essayDiaries;
    }
}
