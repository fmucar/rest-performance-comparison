package com.cooldatasoft.sampler;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Random;

import com.cooldatasoft.sampler.util.RandomString;

/**
 * 
 * @author Fatih Mehmet UCAR - fmucar@cooldatasoft.com
 *
 * TODO: Add logging
 * TODO: Length of string fields generated
 */
public class ObjectFactory {

	private static Random random = new Random();

	public static <T> T sample(Class<T> clazz) {
		try {
			T instance = clazz.newInstance();
			Field[] declaredFields = clazz.getDeclaredFields();

			for (Field field : declaredFields) {
				if (Modifier.isFinal(field.getModifiers())) {
					continue;
				}

				Class<?> type = field.getType();
				field.setAccessible(true);

				if (type.isArray()) {
					Class<?> componentType = type.getComponentType();
					int randomArraySize = Math.abs(random.nextInt(20));
					Object arrayInstance = Array.newInstance(componentType, randomArraySize);
					for (int i = 0; i < randomArraySize; i++) {
						Array.set(arrayInstance, i, getRandomValue(field, componentType));
					}
					field.set(instance, arrayInstance);
				} else {
					field.set(instance, getRandomValue(field, type));
				}
			}
			return instance;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Object getRandomValue(Field field, Class<?> type) {
		if (Integer.class.isAssignableFrom(type) || int.class.isAssignableFrom(type)) {
			return random.nextInt();
		} else if (Long.class.isAssignableFrom(type) || long.class.isAssignableFrom(type)) {
			return random.nextLong();
		} else if (Short.class.isAssignableFrom(type) || short.class.isAssignableFrom(type)) {
			return (short) (Math.random() * Short.MAX_VALUE);
		} else if (Boolean.class.isAssignableFrom(type) || boolean.class.isAssignableFrom(type)) {
			return random.nextBoolean();
		} else if (Double.class.isAssignableFrom(type) || double.class.isAssignableFrom(type)) {
			return random.nextDouble();
		} else if (Float.class.isAssignableFrom(type) || float.class.isAssignableFrom(type)) {
			return random.nextFloat();
		} else if (Byte.class.isAssignableFrom(type) || byte.class.isAssignableFrom(type)) {
			byte[] randomByte = new byte[1];
			random.nextBytes(randomByte);
			return randomByte[0];
		} else if (Character.class.isAssignableFrom(type) || char.class.isAssignableFrom(type)) {
			return RandomString.getRandomChar(true, true, true);
		} else if (String.class.isAssignableFrom(type)) {
			return RandomString.getString("**********");
		} else if (Date.class.isAssignableFrom(type)) {
			return new Date();
		} else {
			return sample(field.getType());
		}
	}
}
