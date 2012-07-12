package sf.hackday.quotemap.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Locale;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sf.hackday.quotemap.quote.QuoteData;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.zillow._static.xsd.searchresults.Searchresults;
import com.zillow._static.xsd.searchresults.Searchresults.Response.Results;
import com.zillow._static.xsd.zillowtypes.SimpleProperty;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		return "home";
	}

	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public String getDetails(@RequestParam("street") String street, @RequestParam("city") String city,
			@RequestParam("state") String state, @RequestParam("zip") String zip,Locale locale, Model model) {
		logger.info("Getting details");
		String cityStateZip = city + state + zip;
		Client client = Client.create();
		WebResource resource = client.resource("http://www.zillow.com/webservice/GetDeepSearchResults.htm");
		
		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		try {
			queryParams.add("zws-id", "X1-ZWz1ddtz5qfhmz_1651t");
			queryParams.add("address", URLEncoder.encode(street, "UTF-8"));
			queryParams.add("citystatezip", URLEncoder.encode(cityStateZip, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Searchresults searchResults = resource.queryParams(queryParams).get(Searchresults.class);
		Results results = searchResults.getResponse().getResults();
		if (results != null){
			if (!CollectionUtils.isEmpty(results.getResult())){
				SimpleProperty property = results.getResult().get(0);
				BigDecimal insureValue = new BigDecimal(property.getZestimate().getValuationRange().getHigh().getValue());
				insureValue = insureValue.multiply(BigDecimal.valueOf(1.2));
				model.addAttribute("property", property);
				model.addAttribute("insureValue", insureValue.setScale(0,RoundingMode.HALF_UP).toString());
				return "details";
			}
		}
		return "error";
	}
	
	@RequestMapping(value = "/quote", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON)
	@ResponseBody
	public String getQuote(@ModelAttribute("quoteData") QuoteData quoteData) {
		logger.info("Calculating Quote.... " + quoteData.getBathrooms());
		return quoteData.calculateQuote();
	}
	
}
