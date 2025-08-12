package com.hotelsbook.services.domain.entities;

import com.hotelsbook.services.dtos.ServicesByHotelsDto;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.StoredProcedureParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedStoredProcedureQuery(
	name = "serviciosPorHoteles",
	procedureName = "GetServicesByHotels",
//	resultClasses = HotelServiceEntity.class,
	parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "hotelIds", type = String.class )
	},
	resultSetMappings = "ServicesByHotelsDTOMapping" // referencia al mapping de abajo
)
@SqlResultSetMapping(
	name = "ServicesByHotelsDTOMapping",
	classes = @ConstructorResult(
		targetClass = ServicesByHotelsDto.class,
		columns = {
			@ColumnResult(name = "hotel_id", type = Long.class),
			@ColumnResult(name = "hotel_name", type = String.class),
			@ColumnResult(name = "service_name", type = String.class),
			@ColumnResult(name = "service_id", type = Long.class)
		}
	)
)
public class HotelServiceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotelId;
	private String hotelName;		
	private String serviceName;		
	private Long serviceId;
	

}
