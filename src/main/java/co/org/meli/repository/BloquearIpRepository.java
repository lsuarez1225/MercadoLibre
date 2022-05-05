package co.org.meli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.org.meli.model.BlackList;

@Repository
public interface BloquearIpRepository extends JpaRepository<BlackList, Long> {
	
	@Query(value = "SELECT COUNT(a) FROM BlackList a WHERE a.ip = :ip")
	public long consultarIp(@Param("ip") String ip);
}
