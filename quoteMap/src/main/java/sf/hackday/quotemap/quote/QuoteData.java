package sf.hackday.quotemap.quote;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang.StringUtils;


public class QuoteData {
	
	private String yearBuilt;
	private String squareFeet;
	private String bathrooms;
	private String bedrooms;
	private String lotSize;
	private String propertyValue;
	private String insureValue;
	private String totalRooms;
	
	public String calculateQuote(){
		BigDecimal quote = new BigDecimal(insureValue);
		quote = quote.multiply(BigDecimal.valueOf(.004));
		quote = quote.add(BigDecimal.valueOf(Double.valueOf(bathrooms) * 8));
		quote = quote.add(BigDecimal.valueOf(Double.valueOf(bedrooms) * 5));
		return quote.setScale(0,RoundingMode.HALF_UP).toString();
	}

	public String getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(String yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public String getSquareFeet() {
		return squareFeet;
	}

	public void setSquareFeet(String squareFeet) {
		this.squareFeet = squareFeet;
	}

	public String getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(String bathrooms) {
		if (StringUtils.isEmpty(bathrooms)){
			this.bathrooms = "1";
		}else{
			this.bathrooms = bathrooms;
		}
	}

	public String getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(String bedrooms) {
		if (StringUtils.isEmpty(bedrooms)){
			this.bedrooms = "1";
		}else{
			this.bedrooms = bedrooms;
		}
	}

	public String getLotSize() {
		return lotSize;
	}

	public void setLotSize(String lotSize) {
		this.lotSize = lotSize;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getInsureValue() {
		return insureValue;
	}

	public void setInsureValue(String insureValue) {
		this.insureValue = insureValue;
	}

	public String getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(String totalRooms) {
		this.totalRooms = totalRooms;
	}
	
	
}
