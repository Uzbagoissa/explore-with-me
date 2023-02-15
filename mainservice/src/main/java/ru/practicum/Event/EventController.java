package ru.practicum.Event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.Category.CategoryDto;
import ru.practicum.Category.CategoryService;
import ru.practicum.exceptions.IncorrectParameterException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/events")
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getAllEvents(@RequestParam(value = "text") String text,
                                    @RequestParam(value = "categories") List<Long> categories,
                                    @RequestParam(value = "paid") boolean paid,
                                    @RequestParam(value = "rangeStart") String rangeStart,
                                    @RequestParam(value = "rangeEnd") String rangeEnd,
                                    @RequestParam(value = "onlyAvailable") boolean onlyAvailable,
                                    @RequestParam(value = "sort") String sort,
                                    @RequestParam(value = "from", defaultValue = "0") long from,
                                    @RequestParam(value = "size", defaultValue = "10") long size) {
        if (from < 0) {
            log.info("Неверный параметр from: {}, from должен быть больше или равен 0 ", from);
            throw new IncorrectParameterException("Неверный параметр from: {}, from должен быть больше или равен 0 " + from);
        }
        if (size <= 0) {
            log.info("Неверный параметр size: {}, size должен быть больше или равен 0 ", size);
            throw new IncorrectParameterException("Неверный параметр size: {}, size должен быть больше или равен 0 " + size);
        }
        log.info("События найдены");
        return eventService.getAllEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/events/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Event getEventById(@PathVariable("id") long id) {
        log.info("Событие найдено");
        return eventService.getEventById(id);
    }

    @GetMapping("/users/{userId}/events")
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getAllEventsByUserId(@PathVariable("userId") long userId,
                                            @RequestParam(value = "from", defaultValue = "0") long from,
                                            @RequestParam(value = "size", defaultValue = "10") long size) {
        if (from < 0) {
            log.info("Неверный параметр from: {}, from должен быть больше или равен 0 ", from);
            throw new IncorrectParameterException("Неверный параметр from: {}, from должен быть больше или равен 0 " + from);
        }
        if (size <= 0) {
            log.info("Неверный параметр size: {}, size должен быть больше или равен 0 ", size);
            throw new IncorrectParameterException("Неверный параметр size: {}, size должен быть больше или равен 0 " + size);
        }
        log.info("События найдены");
        return eventService.getAllEventsByUserId(userId, from, size);
    }

    @GetMapping("/users/{userId}/events/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Event getEventByIdAndByUserId(@PathVariable("userId") long userId,
                                         @PathVariable("eventId") long eventId) {
        log.info("Событие найдено");
        return eventService.getEventByIdAndByUserId(userId, eventId);
    }

    @GetMapping("/admin/events")
    @ResponseStatus(HttpStatus.OK)
    public List<Event> getAllEventsAdmin(@RequestParam(value = "usersIds") List<Long> usersIds,
                                         @RequestParam(value = "states") List<String> states,
                                         @RequestParam(value = "categories") List<Long> categories,
                                         @RequestParam(value = "rangeStart") String rangeStart,
                                         @RequestParam(value = "rangeEnd") String rangeEnd,
                                         @RequestParam(value = "from", defaultValue = "0") long from,
                                         @RequestParam(value = "size", defaultValue = "10") long size) {
        if (from < 0) {
            log.info("Неверный параметр from: {}, from должен быть больше или равен 0 ", from);
            throw new IncorrectParameterException("Неверный параметр from: {}, from должен быть больше или равен 0 " + from);
        }
        if (size <= 0) {
            log.info("Неверный параметр size: {}, size должен быть больше или равен 0 ", size);
            throw new IncorrectParameterException("Неверный параметр size: {}, size должен быть больше или равен 0 " + size);
        }
        log.info("События найдены");
        return eventService.getAllEventsAdmin(usersIds, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PostMapping("/users/{userId}/events")
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@PathVariable("userId") long userId,
                           @Valid @RequestBody EventDto eventDto) {
        log.info("Событие добавлено");
        return eventService.saveEvent(userId, eventDto);
    }

    @PatchMapping("/users/{userId}/events/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Event updateEvent(@PathVariable("userId") long userId,
                             @PathVariable("eventId") long eventId,
                             @Valid @RequestBody EventDto eventDto) {
        log.info("Событие обновлено");
        return eventService.updateEvent(userId, eventId, eventDto);
    }

    @PatchMapping("/admin/events/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public Event updateEventAdmin(@PathVariable("eventId") long eventId,
                                  @Valid @RequestBody EventDto eventDto) {
        log.info("Событие отредактировано");
        return eventService.updateEventAdmin(eventId, eventDto);
    }
}