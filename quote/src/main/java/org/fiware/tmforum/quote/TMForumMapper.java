package org.fiware.tmforum.quote;

import io.github.wistefan.mapping.MappingException;
import org.fiware.quote.model.*;
import org.fiware.tmforum.common.domain.Money;
import org.fiware.tmforum.common.domain.subscription.TMForumSubscription;
import org.fiware.tmforum.common.mapping.BaseMapper;
import org.fiware.tmforum.common.mapping.IdHelper;
import org.fiware.tmforum.product.*;
import org.fiware.tmforum.resource.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Mapper between the internal model and api-domain objects
 */
@Mapper(componentModel = "jsr330", uses = {IdHelper.class})
public abstract class TMForumMapper extends BaseMapper {

	// catalog

	@Mapping(target = "id", source = "id")
	@Mapping(target = "href", source = "id")
	public abstract QuoteVO map(QuoteCreateVO quoteCreateVO, URI id);

	public abstract QuoteVO map(Quote quote);

	@Mapping(target = "href", source = "id")
	public abstract Quote map(QuoteVO quoteVO);

	@Mapping(target = "id", source = "id")
	public abstract Quote map(QuoteUpdateVO quoteUpdateVO, String id);

	@Mapping(target = "query", source = "rawQuery")
	public abstract EventSubscriptionVO map(TMForumSubscription subscription);

	@Mapping(target = "tmfValue", source = "value")
	public abstract Money map(MoneyVO moneyVO);

	@Mapping(target = "value", source = "tmfValue")
	public abstract MoneyVO map(Money money);

	@Mapping(target = "tmfId", source = "id")
	public abstract Note map(NoteVO noteVO);

	@Mapping(target = "id", source = "tmfId")
	public abstract NoteVO map(Note note);

	@Mapping(target = "tmfId", source = "id")
	public abstract QuoteItem map(QuoteItemVO quoteItemVO);

	@Mapping(target = "id", source = "tmfId")
	public abstract QuoteItemVO map(QuoteItem quoteItem);

	@Mapping(target = "tmfId", source = "id")
	public abstract QuoteItemRelationship map(QuoteItemRelationshipVO quoteItemRelationshipVO);

	@Mapping(target = "id", source = "tmfId")
	public abstract QuoteItemRelationshipVO map(QuoteItemRelationship quoteItemRelationship);

	@Mapping(target = "tmfValue", source = "value")
	public abstract Characteristic map(CharacteristicVO characteristicVO);

	@Mapping(target = "value", source = "tmfValue")
	public abstract CharacteristicVO map(Characteristic characteristic);

	public String mapQuoteItemState(QuoteItemState value) {
		return value.getValue();
	}

	public QuoteItemState mapQuoteItemState(String value) {
		return QuoteItemState.toEnum(value);
	}

	public URL map(String value) {
		if (value == null) {
			return null;
		}
		try {
			return new URL(value);
		} catch (MalformedURLException e) {
			throw new MappingException(String.format("%s is not a URL.", value), e);
		}
	}

	public String map(URL value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	public URI mapToURI(String value) {
		if (value == null) {
			return null;
		}
		return URI.create(value);
	}

	public String mapFromURI(URI value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}
}


