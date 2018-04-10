package com.payAm.core.util;//package com.payAm.core.util;
//
//import com.avaje.ebean.annotation.DbJsonB;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.payAm.core.constant.BaseConstants;
//import org.apache.commons.beanutils.PropertyUtils;
//
//import javax.persistence.*;
//import java.lang.reflect.*;
//import java.math.BigDecimal;
//import java.util.*;
//
///**
// * Created by Payam Mostafaei
// * Creation Time: 2017/Jan/05 - 07:29 PM
// */
//public class ReflectionUtil {
//
//	private static List<Class<?>> basicTypes;
//	private static Map<Class<?>, Object> primitiveDefaultValues;
//	private static List<Class<?>> associationTypes;
//	private static List<Class<?>> columnTypes;
//	static {
//		basicTypes = new ArrayList<>();
//		basicTypes.add(Character.class);
//		basicTypes.add(Short.class);
//		basicTypes.add(Integer.class);
//		basicTypes.add(Long.class);
//		basicTypes.add(Number.class);
//		basicTypes.add(Float.class);
//		basicTypes.add(Double.class);
//		basicTypes.add(String.class);
//		basicTypes.add(Date.class);
//		basicTypes.add(Boolean.class);
//		basicTypes.add(BigDecimal.class);
//		basicTypes.add(Byte[].class);
//		basicTypes.add(byte[].class);
//		basicTypes.add(JsonNode.class);
//
//		primitiveDefaultValues = new HashMap<>();
//		primitiveDefaultValues.put(boolean.class, Array.get(Array.newInstance(boolean.class, 1), 0));
//		primitiveDefaultValues.put(short.class, Array.get(Array.newInstance(short.class, 1), 0));
//		primitiveDefaultValues.put(int.class, Array.get(Array.newInstance(int.class, 1), 0));
//		primitiveDefaultValues.put(long.class, Array.get(Array.newInstance(long.class, 1), 0));
//		primitiveDefaultValues.put(float.class, Array.get(Array.newInstance(float.class, 1), 0));
//		primitiveDefaultValues.put(double.class, Array.get(Array.newInstance(double.class, 1), 0));
//		primitiveDefaultValues.put(byte.class, Array.get(Array.newInstance(byte.class, 1), 0));
//		primitiveDefaultValues.put(char.class, Array.get(Array.newInstance(char.class, 1), 0));
//
//		associationTypes = new ArrayList<>();
//		associationTypes.add(OneToOne.class);
//		associationTypes.add(OneToMany.class);
//		associationTypes.add(ManyToOne.class);
//		associationTypes.add(ManyToMany.class);
//
//		columnTypes = new ArrayList<>();
//		columnTypes.add(Column.class);
//		columnTypes.add(DbJsonB.class);
//		columnTypes.add(Lob.class);
//		columnTypes.add(Id.class);
//		columnTypes.add(Version.class);
//	}
//
//	public static Object getPrimitiveDefaultValue(Class<?> primitiveClass) {
//		return primitiveDefaultValues.get(primitiveClass);
//	}
//
//	public static List<Class<?>> getBasicTypes() {
//		return basicTypes;
//	}
//
//	public static boolean isBasicType(Class<?> clazz) {
//		return basicTypes.contains(clazz);
//	}
//
//	public static boolean isPrimitiveOrBasicType(Class<?> clazz) {
//		return clazz.isPrimitive() || basicTypes.contains(clazz);
//	}
//
//	public static boolean isPrimitiveOrBasicType(Object obj) {
//		return isPrimitiveOrBasicType(obj.getClass());
//	}
//
//	public static boolean isReturnTypePrimitiveOrBasicType(Method method) {
//		return isPrimitiveOrBasicType(method.getReturnType()) || "getId".equals(method.getName());
//	}
//
//	public static boolean isPrimitivePropertyGetterMethod(Method method) {
//		return method.getName().startsWith("get") && method.getReturnType().isPrimitive();
//	}
//
//	public static boolean isBasicPropertyGetterMethod(Method method) {
//		return method.getName().startsWith("get") && (isBasicType(method.getReturnType()) || "getId".equals(method.getName()));
//	}
//
//	public static boolean isPrimitiveOrBasicPropertyGetterMethod(Method method) {
//		return method.getName().startsWith("get") && (isPrimitiveOrBasicType(method.getReturnType()) || "getId".equals(method.getName()));
//	}
//
//	public static Method getGetterMethod(Object object, String propertyName) {
//		String getterMethodName = "get" + StringUtil.toFirstUpper(propertyName);
//		try {
//			return object.getClass().getMethod(getterMethodName);
//		} catch (NoSuchMethodException | SecurityException e) {
//			return null;
//		}
//	}
//
//	public static boolean isEnum(Object obj) {
//		return obj.getClass().getSuperclass() != null && obj.getClass().getSuperclass().equals(Enum.class);
//	}
//
//	public static boolean isCollectionOfEnum(Collection collection) {
//		if (collection != null && collection.size() > 0) {
//			for (Object item : collection) {
//				return ReflectionUtil.isEnum(item);
//			}
//		}
//		return false;
//	}
//
//	public static boolean isCollectionOfPrimitive(Collection collection) {
//		if (collection != null && collection.size() > 0) {
//			for (Object item : collection) {
//				return ReflectionUtil.isPrimitiveOrBasicType(item);
//			}
//		}
//		return false;
//	}
//
//	public static List<?> getAsList(Collection<?> collection) {
//		List<?> returnList = null;
//		if (collection instanceof List) {
//			returnList = (List<?>) collection;
//		} else {
//			returnList = new ArrayList<Object>(collection);
//		}
//		return returnList;
//	}
//
//	public static String getFieldName(String methodName) {
//		return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
//	}
//
//	public static Object invokeGetter(Object object, String field) {
//		Object result = null;
//		if (field.indexOf('.') > 0) {
//			String firstProperty = field.substring(0, field.indexOf('.'));
//			String restFields = field.substring(field.indexOf('.') + 1, field.length());
//			Object firstObject = invokeGetter(object, firstProperty);
//			if (firstObject == null) {
//				return null;
//			}
//			result = invokeGetter(firstObject, restFields);
//			return result;
//		}
//		try {
//			result = PropertyUtils.getProperty(object, field);
//		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//		}
//		return result;
//	}
//
//	public static Object createInstanceAndSetFields(Class<?> clazz, HashMap<String, Object> fieldToValuesMap) {
//		try {
//			Object bean = clazz.newInstance();
//			for (String field : fieldToValuesMap.keySet()) {
//				Object value = fieldToValuesMap.get(field);
//				if (field.contains(".")) {
//					Object currentBean = bean;
//					String currentField = field;
//					while (currentField.contains(".")) {
//						String currentBeanName = currentField.substring(0, currentField.indexOf('.'));
//						if (PropertyUtils.getProperty(currentBean, currentBeanName) != null) {
//							currentBean = PropertyUtils.getProperty(currentBean, currentBeanName);
//						} else {
//							Method currentBeanGetterMethod = currentBean.getClass()
//									.getMethod(ReflectionUtil.createGetterMethodNameFromFieldName(currentBeanName));
//							Object newInnerBean = currentBeanGetterMethod.getReturnType().newInstance();
//							PropertyUtils.setProperty(currentBean, currentBeanName, newInnerBean);
//							currentBean = newInnerBean;
//						}
//						if (currentBean instanceof List || currentBean instanceof Set || currentBean instanceof Map) {
//							IllegalStateException illegalStateException = new IllegalStateException(
//									"FilterPropertyUtil.createInstanceAndSetProperties() method can not be applied on Set, List or Map.");
//							throw illegalStateException;
//						}
//						currentField = currentField.substring(currentField.indexOf('.') + 1);
//					}
//					PropertyUtils.setProperty(currentBean, currentField, value);
//				} else {
//					PropertyUtils.setProperty(bean, field, value);
//				}
//			}
//			return bean;
//		} catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//		}
//		return null;
//	}
//
//	public static boolean isNotGeneratedGetterMethod(String getterMethodName) {
//		return !getterMethodName.contains("get$JAVASSIST_READ_WRITE_HANDLER") && !getterMethodName.contains("$$EnhancerByCGLIB")
//				&& !getterMethodName.contains("$$_javassist") && !getterMethodName.contains("$$_jvst")
//				&& !getterMethodName.startsWith("get$$_hibernate_") & !getterMethodName.contains("get_filter_signature")
//				&& !getterMethodName.contains("getSerialVersionUID") && !getterMethodName.contains("get_methods_") && !getterMethodName.contains("getHandler");
//	}
//
//	public static String makeFieldFullPath(String parentPath, String fieldName) {
//		if (StringUtil.hasText(parentPath)) {
//			return parentPath + "." + fieldName;
//		}
//		return fieldName;
//	}
//
//	public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
//		Method method;
//		try {
//			method = clazz.getMethod(methodName, parameterTypes);
//		} catch (Exception e) {
//			throw new IllegalStateException(e);
//		}
//		return method;
//	}
//
//	public static Field getField(Class<?> clazz, String fieldName) {
//		Field field;
//		try {
//			field = clazz.getField(fieldName);
//		}
//		catch (Exception e) {
//			throw new IllegalStateException(e);
//		}
//		return field;
//	}
//
//	public static Class<?> getIdType(Class<?> clazz) {
//		return ReflectionUtil.getFieldType(clazz, BaseConstants.ID);
//	}
//
//	public static Class<?> getFieldType(Class<?> clazz, String fieldName) {
//		return ReflectionUtil.getMethod(clazz, createGetterMethodNameFromFieldName(fieldName)).getReturnType();
//	}
//
//	public static Object invokeMethod(Method method, Object object, Object... parameters) {
//		try {
//			return method.invoke(object, parameters);
//		} catch (Exception e) {
//			throw new IllegalStateException(e);
//		}
//	}
//
//	public static void setProperty(Object object, String propertyName, Object value) {
//		try {
//			Method[] methods = object.getClass().getMethods();
//			String methodName = "set" + StringUtil.toFirstUpper(propertyName);
//			for (Method method : methods) {
//				if (method.getName().equals(methodName)) {
//					method.invoke(object, value);
//				}
//			}
//		} catch (Exception e) {
//			// Do nothing.
//		}
//	}
//
//	public static List<Field> getAllFields(Object object) {
//		Class objectClass = object.getClass();
//		List<Field> fields = new ArrayList<Field>(Arrays.asList(objectClass.getDeclaredFields()));
//		Class superClass = objectClass.getSuperclass();
//		while (superClass != null) {
//			try {
//				Field field = superClass.getDeclaredField(BaseConstants.ID);
//				fields.add(0, field);
//				break;
//			} catch (Exception e) {
//				superClass = superClass.getSuperclass();
//			}
//		}
//		for (Iterator<Field> iterator = fields.iterator(); iterator.hasNext();) {
//			Field field = (Field) iterator.next();
//			if ((field.getModifiers() & Modifier.STATIC) != 0 || (field.getModifiers() & Modifier.FINAL) != 0) {
//				iterator.remove();
//			}
//		}
//		return fields;
//	}
//
//	public static List<String> getAllFieldNames(Object object) {
//		List<Field> allFields = getAllFields(object);
//		List<String> fieldNames = new ArrayList<String>();
//		for (Field field : allFields) {
//			fieldNames.add(field.getName());
//		}
//		return fieldNames;
//	}
//
//	public static String createGetterMethodNameFromFieldName(String fieldName) {
//		return StringUtil.hasText(fieldName) ? "get" + StringUtil.toFirstUpper(fieldName) : "";
//	}
//
//	public static String createFieldNameFromGetterMethodName(String getterMethodName) {
//		return StringUtil.hasText(getterMethodName) ? StringUtil.toFirstLower(getterMethodName.substring("get".length())) : "";
//	}
//
//	public static boolean isEntityColumn(Field field) {
//
//		for (Class columnClass : columnTypes) {
//			if (field.getAnnotation(columnClass) != null) {
//				return true;
//			}
//		}
//		return field.getAnnotations().length == 0;
//	}
//
//	public static boolean isAssociation(Field field) {
//
//		for (Class associationClass : associationTypes) {
//			if (field.getAnnotation(associationClass) != null) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public static List<Field> getAllEntityColumns(Class objectClass) {
//		List<Field> fields = new ArrayList<>(Arrays.asList(objectClass.getDeclaredFields()));
//		Class superClass = objectClass.getSuperclass();
//		while (superClass != null) {
//			Field[] superClassFields = superClass.getDeclaredFields();
//			for (Field field : superClassFields)
//				if (isEntityColumn(field))
//					fields.add(0, field);
//			superClass = superClass.getSuperclass();
//		}
//		for (Iterator<Field> iterator = fields.iterator(); iterator.hasNext();) {
//			Field field = iterator.next();
//			if ((field.getModifiers() & Modifier.STATIC) != 0 || (field.getModifiers() & Modifier.FINAL) != 0) {
//				iterator.remove();
//			}
//		}
//		return fields;
//	}
//
//	public static List<String> getAllEntityColumnNames(Class objectClass) {
//		List<Field> fields = getAllEntityColumns(objectClass);
//		List<String> fieldNames = new ArrayList<>();
//		for (Field field : fields) {
//			if (!field.getName().contains("_ebean")) {
//				fieldNames.add(field.getName());
//			}
//		}
//
//		return fieldNames;
//	}
//
//}
