package es.codeurjc.board;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class PostService {

	private final ConcurrentMap<Long, Post> posts = new ConcurrentHashMap<>();
	private final AtomicLong nextId = new AtomicLong(1);

	public PostService() {
		save(new Post("Pepe", "Vendo moto", "Barata, barata"));
		save(new Post("Juan", "Compro coche", "Pago bien"));
	}

	public Collection<Post> findAll() {
		return posts.values();
	}

	public Post findById(long id) {
		return posts.get(id);
	}

	public Post save(Post post) {

		if(post.getId() == null || post.getId() == 0) {
			long id = nextId.getAndIncrement();
			post.setId(id);
		}

		this.posts.put(post.getId(), post);

		return post;
	}

	public void deleteById(long id) {
		this.posts.remove(id);
	}

}
