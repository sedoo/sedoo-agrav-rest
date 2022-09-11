package fr.sedoo.agrav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.sedoo.agrav.domain.Car;


@RestController
@CrossOrigin
@RequestMapping(value = "/station")
public class StationService {

	@Autowired
	JdbcTemplate template;

	@RequestMapping(value = "/cars", method = RequestMethod.GET)
	public List<Car> cars() {
		String sql = "SELECT * FROM cars";
		List<Car> cars = template.query(sql, new BeanPropertyRowMapper<>(Car.class));
		return cars;
	}
}
