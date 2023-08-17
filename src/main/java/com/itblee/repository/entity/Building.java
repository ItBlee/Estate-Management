package com.itblee.repository.entity;

import com.itblee.converter.RentTypeConverter;
import com.itblee.repository.enums.District;
import com.itblee.repository.enums.RentType;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "building")
public class Building extends BaseEntityAudit {

    private static final long serialVersionUID = 4499222508663949510L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Integer numberOfBasement;

    @Column(name = "floorarea")
    private Integer floorArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private String level;

    @Column(name = "rentprice", nullable = false)
    private Integer rentPrice;

    @Column(name = "rentpricedescription")
    private String rentPriceDescription;

    @Column(name = "servicefee")
    private String serviceFee;

    @Column(name = "carfee")
    private String carFee;

    @Column(name = "motorbikefee")
    private String motorbikeFee;

    @Column(name = "overtimefee")
    private String overtimeFee;

    @Column(name = "waterfee")
    private String waterFee;

    @Column(name = "electricityfee")
    private String electricityFee;

    @Column(name = "deposit")
    private String deposit;

    @Column(name = "payment")
    private String payment;

    @Column(name = "renttime")
    private String rentTime;

    @Column(name = "decorationtime")
    private String decorationTime;

    @Column(name = "brokeragefee")
    private Double brokerageFee;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphone")
    private String managerPhone;

    @Column(name = "note")
    private String note;

    @Column(name = "linkofbuilding")
    private String linkOfBuilding;

    @Column(name = "map")
    private String map;

    @Column(name = "avatar")
    private String avatarName;

    @Enumerated(EnumType.STRING)
    @Column(name = "district")
    private District districtCode;

    @Convert(converter = RentTypeConverter.class)
    @Column(name = "type")
    private List<RentType> rentTypeCodes = new ArrayList<>();

    @OneToMany(mappedBy = "building",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = true)
    private List<RentArea> rentAreas = new ArrayList<>();

    @ManyToMany()
    @JoinTable(name = "assignmentbuilding",
            joinColumns = @JoinColumn(name = "buildingid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private Set<User> assignUsers = new HashSet<>();

    public Building() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Integer floorArea) {
        this.floorArea = floorArea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getCarFee() {
        return carFee;
    }

    public void setCarFee(String carFee) {
        this.carFee = carFee;
    }

    public String getMotorbikeFee() {
        return motorbikeFee;
    }

    public void setMotorbikeFee(String motorbikeFee) {
        this.motorbikeFee = motorbikeFee;
    }

    public String getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(String overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDecorationTime() {
        return decorationTime;
    }

    public void setDecorationTime(String decorationTime) {
        this.decorationTime = decorationTime;
    }

    public Double getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Double brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLinkOfBuilding() {
        return linkOfBuilding;
    }

    public void setLinkOfBuilding(String linkOfBuilding) {
        this.linkOfBuilding = linkOfBuilding;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public District getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(District districtCode) {
        this.districtCode = districtCode;
    }

    public List<RentType> getRentTypeCodes() {
        return rentTypeCodes;
    }

    public void setRentTypeCodes(List<RentType> rentTypeCodes) {
        this.rentTypeCodes = rentTypeCodes;
    }

    public List<RentArea> getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(List<RentArea> rentAreas) {
        this.rentAreas = rentAreas;
    }

    public Set<User> getAssignUsers() {
        return assignUsers;
    }

    public void setAssignUsers(Set<User> assignUsers) {
        this.assignUsers = assignUsers;
    }

    public void addRentAreas(RentArea rentArea) {
        rentArea.setBuilding(this);
        this.rentAreas.add(rentArea);
    }

    public void removeRentAreas(RentArea rentArea) {
        rentArea.setBuilding(null);
        this.rentAreas.remove(rentArea);
    }

    public void mergeRentAreas(List<RentArea> rentAreas) {
        for (int i = 0; i < this.rentAreas.size(); i++) {
            RentArea rentArea = this.rentAreas.get(i);
            Optional<RentArea> match = rentAreas.stream()
                    .filter(area -> area.getValue().equals(rentArea.getValue()))
                    .findAny();
            if (match.isPresent())
                this.rentAreas.set(i, match.get());
        }
    }

    public void addUser(User user) {
        this.assignUsers.add(user);
        user.getAssignBuildings().add(this);
    }

    public void removeUser(User user) {
        this.assignUsers.remove(user);
        user.getAssignBuildings().remove(this);
    }

}
