package SchoolInformationAPI.rest;

import SchoolInformationAPI.Exception.NotFoundException;
import SchoolInformationAPI.model.IEntity;
import SchoolInformationAPI.rest.util.RestUtils;
import SchoolInformationAPI.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BasicController<T, S, D> {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected final T service;

    @Autowired
    public BasicController(T service) {
        this.service = service;
    }

    /**
     * Get all entities.
     *
     * @method  GET
     * @autor   Grigoryev Nikita
     * @date    2019-11-17
     * @return  List with courses.
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<S> getEntities() {
        return ((BaseService<S, D>)service).findAll();
    }

    /**
     * Create entity.
     *
     * @method  POST
     * @param   entity
     * @autor   Grigoryev Nikita
     * @date    2019-11-14
     * @return  Response with headers and http status.
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody S entity) {
        ((BaseService<S, D>)service).persist(entity);
        LOG.debug("Created entity {}.", entity);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity)entity).getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Get entity by id.
     *
     * @method  GET
     * @param   id
     * @autor   Grigoryev Nikita
     * @date    2019-11-14
     * @return  Entity.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public S getById(@PathVariable("id") Integer id) {
        final S entity =  ((BaseService<S, D>)service).find(id);
        if (entity == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }
        return entity;
    }

    /**
     * Update entity by id.
     *
     * @method  POST
     * @param   id
     * @author  Grigoryev Nikita
     * @date    2020-01-05
     * @return  Entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateEntity(@RequestBody S entityToUpdate, @PathVariable("id") Integer id) {
        final S entity = ((BaseService<S, D>)service).find(id);
        if (entity == null) {
            throw NotFoundException.create(this.getClass().getSimpleName(), id);
        }
        ((IEntity)entityToUpdate).setId(((IEntity)entity).getId());
        ((BaseService<S, D>)service).update(entityToUpdate);

        LOG.debug("Updated entity {}.", entityToUpdate);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", ((IEntity)entityToUpdate).getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEntity(@PathVariable("id") Integer id) {
        final S entity = ((BaseService<S, D>)service).find(id);
        if (entity != null) {
            ((BaseService<S, D>)service).remove(entity);
        }
        LOG.debug("Removed entity {}.", entity);
    }
}
