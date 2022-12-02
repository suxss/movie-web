package com.movie.web.plugins.filter.proto;

public final class BloomFilterProtos {
    private BloomFilterProtos() {}
    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }
    public interface BloomFilterOrBuilder extends
            // @@protoc_insertion_point(interface_extends:com.movie.web.plugins.filter.proto.BloomFilter)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>required uint32 numHashFunctions = 1;</code>
         * @return Whether the numHashFunctions field is set.
         */
        boolean hasNumHashFunctions();
        /**
         * <code>required uint32 numHashFunctions = 1;</code>
         * @return The numHashFunctions.
         */
        int getNumHashFunctions();

        /**
         * <code>repeated fixed64 bitset = 2;</code>
         * @return A list containing the bitset.
         */
        java.util.List<java.lang.Long> getBitsetList();
        /**
         * <code>repeated fixed64 bitset = 2;</code>
         * @return The count of bitset.
         */
        int getBitsetCount();
        /**
         * <code>repeated fixed64 bitset = 2;</code>
         * @param index The index of the element to return.
         * @return The bitset at the given index.
         */
        long getBitset(int index);
    }
    /**
     * Protobuf type {@code com.movie.web.plugins.filter.proto.BloomFilter}
     */
    public static final class BloomFilter extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:com.movie.web.plugins.filter.proto.BloomFilter)
            BloomFilterOrBuilder {
        private static final long serialVersionUID = 0L;
        // Use BloomFilter.newBuilder() to construct.
        private BloomFilter(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private BloomFilter() {
            bitset_ = emptyLongList();
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(
                UnusedPrivateParameter unused) {
            return new BloomFilter();
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return this.unknownFields;
        }
        private BloomFilter(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            int mutable_bitField0_ = 0;
            com.google.protobuf.UnknownFieldSet.Builder unknownFields =
                    com.google.protobuf.UnknownFieldSet.newBuilder();
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        case 8: {
                            bitField0_ |= 0x00000001;
                            numHashFunctions_ = input.readUInt32();
                            break;
                        }
                        case 17: {
                            if (!((mutable_bitField0_ & 0x00000002) != 0)) {
                                bitset_ = newLongList();
                                mutable_bitField0_ |= 0x00000002;
                            }
                            bitset_.addLong(input.readFixed64());
                            break;
                        }
                        case 18: {
                            int length = input.readRawVarint32();
                            int limit = input.pushLimit(length);
                            if (!((mutable_bitField0_ & 0x00000002) != 0) && input.getBytesUntilLimit() > 0) {
                                bitset_ = newLongList();
                                mutable_bitField0_ |= 0x00000002;
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                bitset_.addLong(input.readFixed64());
                            }
                            input.popLimit(limit);
                            break;
                        }
                        default: {
                            if (!parseUnknownField(
                                    input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                if (((mutable_bitField0_ & 0x00000002) != 0)) {
                    bitset_.makeImmutable(); // C
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.movie.web.plugins.filter.proto.BloomFilterProtos.internal_static_org_inlighting_proto_BloomFilter_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.movie.web.plugins.filter.proto.BloomFilterProtos.internal_static_org_inlighting_proto_BloomFilter_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter.class, com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter.Builder.class);
        }

        private int bitField0_;
        public static final int NUMHASHFUNCTIONS_FIELD_NUMBER = 1;
        private int numHashFunctions_;
        /**
         * <code>required uint32 numHashFunctions = 1;</code>
         * @return Whether the numHashFunctions field is set.
         */
        @java.lang.Override
        public boolean hasNumHashFunctions() {
            return ((bitField0_ & 0x00000001) != 0);
        }
        /**
         * <code>required uint32 numHashFunctions = 1;</code>
         * @return The numHashFunctions.
         */
        @java.lang.Override
        public int getNumHashFunctions() {
            return numHashFunctions_;
        }

        public static final int BITSET_FIELD_NUMBER = 2;
        private com.google.protobuf.Internal.LongList bitset_;
        /**
         * <code>repeated fixed64 bitset = 2;</code>
         * @return A list containing the bitset.
         */
        @java.lang.Override
        public java.util.List<java.lang.Long>
        getBitsetList() {
            return bitset_;
        }
        /**
         * <code>repeated fixed64 bitset = 2;</code>
         * @return The count of bitset.
         */
        public int getBitsetCount() {
            return bitset_.size();
        }
        /**
         * <code>repeated fixed64 bitset = 2;</code>
         * @param index The index of the element to return.
         * @return The bitset at the given index.
         */
        public long getBitset(int index) {
            return bitset_.getLong(index);
        }

        private byte memoizedIsInitialized = -1;
        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            if (!hasNumHashFunctions()) {
                memoizedIsInitialized = 0;
                return false;
            }
            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (((bitField0_ & 0x00000001) != 0)) {
                output.writeUInt32(1, numHashFunctions_);
            }
            for (int i = 0; i < bitset_.size(); i++) {
                output.writeFixed64(2, bitset_.getLong(i));
            }
            unknownFields.writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (((bitField0_ & 0x00000001) != 0)) {
                size += com.google.protobuf.CodedOutputStream
                        .computeUInt32Size(1, numHashFunctions_);
            }
            {
                int dataSize = 0;
                dataSize = 8 * getBitsetList().size();
                size += dataSize;
                size += 1 * getBitsetList().size();
            }
            size += unknownFields.getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter)) {
                return super.equals(obj);
            }
            com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter other = (com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter) obj;

            if (hasNumHashFunctions() != other.hasNumHashFunctions()) return false;
            if (hasNumHashFunctions()) {
                if (getNumHashFunctions()
                        != other.getNumHashFunctions()) return false;
            }
            if (!getBitsetList()
                    .equals(other.getBitsetList())) return false;
            if (!unknownFields.equals(other.unknownFields)) return false;
            return true;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            if (hasNumHashFunctions()) {
                hash = (37 * hash) + NUMHASHFUNCTIONS_FIELD_NUMBER;
                hash = (53 * hash) + getNumHashFunctions();
            }
            if (getBitsetCount() > 0) {
                hash = (37 * hash) + BITSET_FIELD_NUMBER;
                hash = (53 * hash) + getBitsetList().hashCode();
            }
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }
        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }
        /**
         * Protobuf type {@code com.movie.web.plugins.filter.proto.BloomFilter}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:com.movie.web.plugins.filter.proto.BloomFilter)
                com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilterOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.movie.web.plugins.filter.proto.BloomFilterProtos.internal_static_org_inlighting_proto_BloomFilter_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.movie.web.plugins.filter.proto.BloomFilterProtos.internal_static_org_inlighting_proto_BloomFilter_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter.class, com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter.Builder.class);
            }

            // Construct using com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }
            @java.lang.Override
            public Builder clear() {
                super.clear();
                numHashFunctions_ = 0;
                bitField0_ = (bitField0_ & ~0x00000001);
                bitset_ = emptyLongList();
                bitField0_ = (bitField0_ & ~0x00000002);
                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.movie.web.plugins.filter.proto.BloomFilterProtos.internal_static_org_inlighting_proto_BloomFilter_descriptor;
            }

            @java.lang.Override
            public com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter getDefaultInstanceForType() {
                return com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter.getDefaultInstance();
            }

            @java.lang.Override
            public com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter build() {
                com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter buildPartial() {
                com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter result = new com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter(this);
                int from_bitField0_ = bitField0_;
                int to_bitField0_ = 0;
                if (((from_bitField0_ & 0x00000001) != 0)) {
                    result.numHashFunctions_ = numHashFunctions_;
                    to_bitField0_ |= 0x00000001;
                }
                if (((bitField0_ & 0x00000002) != 0)) {
                    bitset_.makeImmutable();
                    bitField0_ = (bitField0_ & ~0x00000002);
                }
                result.bitset_ = bitset_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @java.lang.Override
            public Builder clone() {
                return super.clone();
            }
            @java.lang.Override
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.setField(field, value);
            }
            @java.lang.Override
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return super.clearField(field);
            }
            @java.lang.Override
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return super.clearOneof(oneof);
            }
            @java.lang.Override
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, java.lang.Object value) {
                return super.setRepeatedField(field, index, value);
            }
            @java.lang.Override
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    java.lang.Object value) {
                return super.addRepeatedField(field, value);
            }
            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter) {
                    return mergeFrom((com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter other) {
                if (other == com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter.getDefaultInstance()) return this;
                if (other.hasNumHashFunctions()) {
                    setNumHashFunctions(other.getNumHashFunctions());
                }
                if (!other.bitset_.isEmpty()) {
                    if (bitset_.isEmpty()) {
                        bitset_ = other.bitset_;
                        bitField0_ = (bitField0_ & ~0x00000002);
                    } else {
                        ensureBitsetIsMutable();
                        bitset_.addAll(other.bitset_);
                    }
                    onChanged();
                }
                this.mergeUnknownFields(other.unknownFields);
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                if (!hasNumHashFunctions()) {
                    return false;
                }
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }
            private int bitField0_;

            private int numHashFunctions_ ;
            /**
             * <code>required uint32 numHashFunctions = 1;</code>
             * @return Whether the numHashFunctions field is set.
             */
            @java.lang.Override
            public boolean hasNumHashFunctions() {
                return ((bitField0_ & 0x00000001) != 0);
            }
            /**
             * <code>required uint32 numHashFunctions = 1;</code>
             * @return The numHashFunctions.
             */
            @java.lang.Override
            public int getNumHashFunctions() {
                return numHashFunctions_;
            }
            /**
             * <code>required uint32 numHashFunctions = 1;</code>
             * @param value The numHashFunctions to set.
             * @return This builder for chaining.
             */
            public Builder setNumHashFunctions(int value) {
                bitField0_ |= 0x00000001;
                numHashFunctions_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>required uint32 numHashFunctions = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearNumHashFunctions() {
                bitField0_ = (bitField0_ & ~0x00000001);
                numHashFunctions_ = 0;
                onChanged();
                return this;
            }

            private com.google.protobuf.Internal.LongList bitset_ = emptyLongList();
            private void ensureBitsetIsMutable() {
                if (!((bitField0_ & 0x00000002) != 0)) {
                    bitset_ = mutableCopy(bitset_);
                    bitField0_ |= 0x00000002;
                }
            }
            /**
             * <code>repeated fixed64 bitset = 2;</code>
             * @return A list containing the bitset.
             */
            public java.util.List<java.lang.Long>
            getBitsetList() {
                return ((bitField0_ & 0x00000002) != 0) ?
                        java.util.Collections.unmodifiableList(bitset_) : bitset_;
            }
            /**
             * <code>repeated fixed64 bitset = 2;</code>
             * @return The count of bitset.
             */
            public int getBitsetCount() {
                return bitset_.size();
            }
            /**
             * <code>repeated fixed64 bitset = 2;</code>
             * @param index The index of the element to return.
             * @return The bitset at the given index.
             */
            public long getBitset(int index) {
                return bitset_.getLong(index);
            }
            /**
             * <code>repeated fixed64 bitset = 2;</code>
             * @param index The index to set the value at.
             * @param value The bitset to set.
             * @return This builder for chaining.
             */
            public Builder setBitset(
                    int index, long value) {
                ensureBitsetIsMutable();
                bitset_.setLong(index, value);
                onChanged();
                return this;
            }
            /**
             * <code>repeated fixed64 bitset = 2;</code>
             * @param value The bitset to add.
             * @return This builder for chaining.
             */
            public Builder addBitset(long value) {
                ensureBitsetIsMutable();
                bitset_.addLong(value);
                onChanged();
                return this;
            }
            /**
             * <code>repeated fixed64 bitset = 2;</code>
             * @param values The bitset to add.
             * @return This builder for chaining.
             */
            public Builder addAllBitset(
                    java.lang.Iterable<? extends java.lang.Long> values) {
                ensureBitsetIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(
                        values, bitset_);
                onChanged();
                return this;
            }
            /**
             * <code>repeated fixed64 bitset = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearBitset() {
                bitset_ = emptyLongList();
                bitField0_ = (bitField0_ & ~0x00000002);
                onChanged();
                return this;
            }
            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }


            // @@protoc_insertion_point(builder_scope:com.movie.web.plugins.filter.proto.BloomFilter)
        }

        // @@protoc_insertion_point(class_scope:com.movie.web.plugins.filter.proto.BloomFilter)
        private static final com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter();
        }

        public static com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        @java.lang.Deprecated public static final com.google.protobuf.Parser<BloomFilter>
                PARSER = new com.google.protobuf.AbstractParser<BloomFilter>() {
            @java.lang.Override
            public BloomFilter parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new BloomFilter(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<BloomFilter> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<BloomFilter> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public com.movie.web.plugins.filter.proto.BloomFilterProtos.BloomFilter getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_org_inlighting_proto_BloomFilter_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_org_inlighting_proto_BloomFilter_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }
    private static  com.google.protobuf.Descriptors.FileDescriptor
            descriptor;
    static {
        java.lang.String[] descriptorData = {
                "\n$src/main/resources/BloomFilter.proto\022\024" +
                        "com.movie.web.plugins.filter.proto\"7\n\013BloomFilter\022\030\n\020n" +
                        "umHashFunctions\030\001 \002(\r\022\016\n\006bitset\030\002 \003(\006B)\n" +
                        "\024com.movie.web.plugins.filter.protoB\021BloomFilterProtos"
        };
        descriptor = com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[] {
                        });
        internal_static_org_inlighting_proto_BloomFilter_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_org_inlighting_proto_BloomFilter_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_org_inlighting_proto_BloomFilter_descriptor,
                new java.lang.String[] { "NumHashFunctions", "Bitset", });
    }

    // @@protoc_insertion_point(outer_class_scope)
}