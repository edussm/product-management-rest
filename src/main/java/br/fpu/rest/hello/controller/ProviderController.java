package br.fpu.rest.hello.controller;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.fpu.rest.hello.domain.Provider;

@RestController
@RequestMapping("/provider")
public class ProviderController {

	private AtomicLong geradorDeIds = new AtomicLong();
	private Map<Long, Provider> providers;

	public ProviderController() {
		providers = new ConcurrentHashMap<Long, Provider>();
		for (int i = 0; i < 4; i++) {
			Long id = geradorDeIds.getAndIncrement();
			providers.put(id, new Provider(id, "Forn #" + id, null, null, null, null));
		}
	}

	@RequestMapping(value = "/{providerId}", method = RequestMethod.GET)
	public ResponseEntity<Provider> getProvider(@PathVariable Long providerId) {
		if (providers.containsKey(providerId)) {
			return ResponseEntity.ok(providers.get(providerId));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Provider>> getProviders() {
		return ResponseEntity.ok(providers.values());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Provider> addProvider(@RequestBody Provider provider) {
		provider.setId(geradorDeIds.getAndIncrement());
		providers.put(provider.getId(), provider);
		return ResponseEntity.ok(provider);
	}

	@RequestMapping(value = "/{providerId}",
			method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProvider(
			@PathVariable Long providerId) {
		if (providers.containsKey(providerId)) {
			providers.remove(providerId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> 
	editProvider(@RequestBody Provider provider) {
		if (provider == null || provider.getId() == null || 
				!providers.containsKey(provider.getId())) {
			return ResponseEntity.notFound().build();
		}
		providers.put(provider.getId(), provider);
		return ResponseEntity.ok().build();
	}

}
